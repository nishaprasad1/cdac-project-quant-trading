package com.algo.util;

import java.util.*;
import com.algo.model.InstrumentOHLC;
import com.zerodhatech.kiteconnect.*;
import com.zerodhatech.models.*;
import com.zerodhatech.ticker.*;

import algo01.Program;
import dao.DbAccess;

public class StrategyORB extends Thread{
	
	public static boolean loadedPrevOHLC=false;

	private KiteConnect kc;
	private Map<Long,InstrumentOHLC> subData;
	public boolean isWebSocketOpen=false;
	private String[] stringInstTokens; 
	private DbAccess dbAccess;
	private String prevDate;
	private MyLogger logger = MyLogger.getMyLogger();
	
	//constructor
	public StrategyORB(Map<Long,InstrumentOHLC> subData,String prevDate){
		//get reference to connection object
		this.subData = subData;
		this.prevDate = prevDate;
		
		//get reference of kite connect
		this.kc = Program.kc;
		dbAccess = new DbAccess();
		//String array required for 
		stringInstTokens = new String[subData.size()];
		//preparing StringArray Quotes
		int i=0;
		for(Map.Entry<Long, InstrumentOHLC> e : subData.entrySet()) {
			stringInstTokens[i] = e.getKey().toString();
			i++;
		}
		
		logger.logEntry("Strategy object build successfully");
	}
	
	//actual strategy
	private void startAlgo(){
		System.out.println("Starting AlGO");
		logger.logEntry("Starting Algo ORB");
		
		if(!loadPrevOHLC()) {
			System.out.println("CANNOT START. ERROR FETCHING PREV OHLC");
			logger.logEntry("Error : Cannot load prev OHLC from DB");
			return;
		}
		
		try {
			
			final KiteTicker tickerProvider = new KiteTicker(kc.getAccessToken(), kc.getApiKey());
			
			//subscribe need final ArrayList
			final ArrayList<Long> tokenList = new ArrayList<Long>(subData.keySet());
			
	        tickerProvider.setOnConnectedListener(new OnConnect() {
	            //@Override
	            public void onConnected() {
	                tickerProvider.subscribe(tokenList);
	                tickerProvider.setMode(tokenList, KiteTicker.modeFull);
	                isWebSocketOpen=true;
	                System.out.println("Connected");
	                logger.logEntry("Ticker Connected");
	            }
	        });

	        tickerProvider.setOnDisconnectedListener(new OnDisconnect() {
	            //@Override
	            public void onDisconnected() {
	                // your code goes here
	            	isWebSocketOpen=false;
	            	System.out.println("Disconnected");
	            	logger.logEntry("Ticker Disconnected");
	            }
	        });

	        tickerProvider.setOnTickerArrivalListener(new OnTicks() {
	            //@Override
	            public void onTicks(ArrayList<Tick> ticks) {
	            	//System.out.println("Tick...");
	            	
	            	if(!loadedPrevOHLC) {
	            		System.out.println("Previous OHLC not loaded.");
	            		logger.logEntry("Previous OHLC not loaded");
	            		return;
	            	}
	            	
	            	logger.logEntry("Tick size : "+ticks.size());
	            	//Iterate over all ticks
	                for(Tick t : ticks) {
	                	//get instrument token of tick
	                	Long tickToken = t.getInstrumentToken();
	                	double ltp = t.getLastTradedPrice();
	                	//get tick company from subData
	                	InstrumentOHLC o = subData.get(tickToken);
	                	if(o!=null && o.getScan()) {
	                		if(ltp>o.getHigh()) {
	                			System.out.println("Hibernate buy signal for : "+tickToken);
	                			o.setScan(false);
	                			//Enter into DB
	                			dbAccess.enterSignal("BUY", o, ltp);
	                		}
	                		if(ltp<o.getLow()) {
	                			System.out.println("Hibernate buy signal for : "+tickToken);
	                			o.setScan(false);
	                			//enter into DB
	                			dbAccess.enterSignal("SELL", o, ltp);
	                		}
	                	}
	                }
	            }
	        });
	        
	        tickerProvider.setTryReconnection(true);
	        //maximum retries and should be greater than 0
	        tickerProvider.setMaximumRetries(10);
	        //set maximum retry interval in seconds
	        tickerProvider.setMaximumRetryInterval(30);

	        /** connects to com.zerodhatech.com.zerodhatech.ticker server for getting live quotes*/
	        tickerProvider.connect();

	        tickerProvider.setMode(tokenList, KiteTicker.modeFull);
	        
	        System.out.println("ORB running...");
	        logger.logEntry("ORB running");
		}catch(Throwable t) {
			logger.logEntry("Error in ORB");
			System.out.println("Error in Strategy : "+t);
			Thread.currentThread().interrupt();
		}
		
	}

	//at 3:30
	public boolean saveToDB() {
		
		try {
			//create clone of subData
			Map<Long,InstrumentOHLC> subToSave = new HashMap<Long, InstrumentOHLC>(subData);
			//get Quotes from API
			 Map<String, Quote> quotes = kc.getQuote(stringInstTokens);
			 //iterate over all Quotes
			 for(String name : quotes.keySet()) {
				 //get OHLV data of quoted
		        OHLC quoteOhlc = quotes.get(name).ohlc;
		        //get matching company from subscribed list
		        InstrumentOHLC c = subToSave.get(Long.parseLong(name));
		        if(c!=null) {
		        	c.setOpen(quoteOhlc.open);c.setClose(quoteOhlc.close);
	        		c.setHigh(quoteOhlc.high);c.setLow(quoteOhlc.low);
		        }
		        //System.out.println(name+" :"+quoteOhlc.high+" :::   L : "+quoteOhlc.low);
		     }
			 
			 //Save to DB
			 dbAccess.saveToDB(subToSave);
			 logger.logEntry("Saved todays OHLC to DB");
			 return true;
			 
		}catch(Throwable e) {
			System.out.println("Updating todays data Failed"+e);
			logger.logEntry("Saving todays data to DB failed :"+e);
			return false;
		}
	}
	
	//at 9:30
	public boolean loadPrevOHLC() {
		try {
			dbAccess.getPrevOHLC(subData,prevDate);
			//dao.loadPrevOHLC();
			loadedPrevOHLC=true;
			logger.logEntry("Loaded prev OHLC");
			return true;
		}catch(Throwable t) {
			System.out.println("DATA Fetching Error : "+t);
			logger.logEntry("Loading prev OHLC failed");
			return false;
		}
		
	}
	
	@Override
	public void run() {
		
		startAlgo();
	}
	
	
	//show todaysOHLC
	public void showOHLC() {
		for(Map.Entry<Long, InstrumentOHLC> e : subData.entrySet()) {
			String o = "";
			o += "Name = ["+e.getValue().getInstrumentTokenNo()+"] : ";
			o += "Open = ["+e.getValue().getOpen()+"] : ";
			o += "High = ["+e.getValue().getHigh()+"] : ";
			o += "Low = ["+e.getValue().getLow()+"] : ";
			o += "Close = ["+e.getValue().getClose()+"].";
				
			//System.out.println(o);
			System.out.println(e);
		}
	}
		
	
}
