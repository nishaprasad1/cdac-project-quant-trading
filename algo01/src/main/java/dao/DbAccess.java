package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.query.Query;

import com.algo.model.InstrumentOHLC;
import com.algo.model.Signal;
import com.algo.util.MyLogger;


public class DbAccess {

	public static SessionFactory sf;
	MyLogger l = MyLogger.getMyLogger();
	
	public DbAccess() {
		if(sf==null) {
			try {
				sf = new Configuration().configure().buildSessionFactory();
			}catch(Throwable e) {
				System.out.println("Error in HB config :"+e);
				l.logEntry("Failed to config Hibernate");
			}
		}
	}
	
	//save data to DB	//3:30
	public boolean saveToDB(Map<Long,InstrumentOHLC> subdata) {
		//get Transaction
		Transaction tx = null;
		
		try(Session s = sf.openSession()){
			//begin
			tx = s.beginTransaction();
		
			for(Map.Entry<Long, InstrumentOHLC> e : subdata.entrySet()) {
				//System.out.println(e.getValue());
				s.saveOrUpdate(e.getValue());
			}
			
			tx.commit();
			System.out.println("SAVED data");
			return true;
		}catch(Exception e) {
			//roll back transaction
			if(tx!=null)
				tx.rollback();
			
			System.out.println("Error :"+e);
			l.logEntry("Hibernate error 01:"+e);
			return false;
		}
		
	}
	
	//get from DB		//9:30
	public boolean getPrevOHLC(Map<Long,InstrumentOHLC> subdata,String prevDate) throws ParseException {
		java.util.Date javaDate = new SimpleDateFormat("dd/MM/yyyy").parse(prevDate); 
		java.sql.Date mysqlDate = new java.sql.Date(javaDate.getTime());
		
		Transaction tx = null;
		
		try(Session s = sf.openSession()){
			tx = s.beginTransaction();
			
			//prepare HQL
			String qs = "FROM InstrumentOHLC i where i.recordDate=:d";
			@SuppressWarnings("deprecation")
			Query q = s.createQuery(qs);
			q.setParameter("d",mysqlDate);
			
			//get date from HQL
			List l = q.getResultList();
			
			//iterate over data that we got
			for(Object e:l) {
				//convert from Object to Modal 
				InstrumentOHLC prevOHLCFromDB = (InstrumentOHLC)e;
				System.out.println("iterating : "+e);
				//get this objects entry in our sub list
				InstrumentOHLC objFromSubList = subdata.get(prevOHLCFromDB.getInstrumentTokenNo());
				
				//if we have this item in our list, update it's OHLC
				if(objFromSubList==null) {
					continue;
				}else {
					objFromSubList.setScan(true);
					objFromSubList.setOpen(prevOHLCFromDB.getOpen());
					objFromSubList.setHigh(prevOHLCFromDB.getHigh());
					objFromSubList.setLow(prevOHLCFromDB.getLow());
					objFromSubList.setClose(prevOHLCFromDB.getClose());
					System.out.println(objFromSubList);
				}
			}
			
			//all went nice
			tx.commit();
			System.out.println("Sub List Updated.");
			return true;
		}catch(Exception e) {
			//roll back
			if(tx!=null)
				tx.rollback();
			System.out.println("Error :"+e);
			l.logEntry("Hibernate error 02:"+e);
			return false;
		}
		
		
	}

	//Enter buy - sell signal to DB
	public boolean enterSignal(String signalType,InstrumentOHLC instrumentOHLC,double ltp) {
		
		Transaction tx=null;
		try(Session s = sf.openSession()){
			
			tx = s.beginTransaction();
			
			Signal signal = new Signal();
			signal.setCompanyId(instrumentOHLC.getInstrumentTokenNo());
			signal.setCompanyName(instrumentOHLC.getInstrumentName());
			signal.setStrategyName("ORB");
			if(signalType=="BUY") {
				//long position
				signal.setBuyPrice(ltp);		
				signal.setSellPrice(ltp*1.02);	//SELL at 2%
				signal.setSignalType("BUY");
				signal.setStopLoss(ltp*0.97);	//SQUARE OFF at -3%
			}else if(signalType=="SELL"){
				//short position
				signal.setSellPrice(ltp);
				signal.setBuyPrice(ltp*0.98);	//buy at -2%
				signal.setSignalType("SELL");
				signal.setStopLoss(ltp*1.03);	//SQUARE OFF at 3%
			}else {
				System.out.println("Please Send proper params.");
				return false;
			}
			
			
			tx.commit();		
			return true;
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			l.logEntry("Hibernate error 03:"+e);
			System.out.println("Error entering signal");
			return false;
		}
		
	}

	//get instruments to subscribe
	public List getInstruments() {
		
		Transaction tx=null;
		try(Session s = sf.openSession()){
			tx = s.beginTransaction();
			
			@SuppressWarnings("deprecation")
			Query q = s.createQuery("FROM InstrumentInfo i");
			List l = q.list();
			
			tx.commit();
			return l;
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			l.logEntry("Hibernate error 04:"+e);
			System.out.println("Failed to get instrument list.");
			return null;
		}
		
	}
}

