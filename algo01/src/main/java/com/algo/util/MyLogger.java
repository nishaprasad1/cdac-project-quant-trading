package com.algo.util;

import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

public class MyLogger {

	private static MyLogger obj=null;
	public static Logger logger =  Logger.getLogger(MyLogger.class.getName());
	
	private MyLogger() {}
	
	public static MyLogger getMyLogger() {
		//builder pattern
		if(obj!=null)
			return obj;
		
		//make object of this class
		MyLogger ml = new MyLogger();
		
		//add default handler
		logger.addHandler(new StreamHandler());
		
		try {
			//add custom file handler
			Handler handler = new FileHandler("algo.logs",2500,21);
			//add string formatter
			handler.setFormatter(new Formatter() {
				@Override
				public String format(LogRecord record) {
					return  record.getSourceClassName()+"::"+new Date(record.getMillis())+"::"+record.getMessage()+"\n";
				}
			});
			//add custom filter
			handler.setFilter(new Filter() {
				@Override
				public boolean isLoggable(LogRecord record) {
					if(record.getLevel() == Level.CONFIG) return false;
					return true;
				}
			});
			//add this handler to logger
			logger.addHandler(handler);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//return this fully configured object
		return ml;
	}
	
	public void logEntry(String msg) {
		//log messages
		try {
			logger.log(Level.INFO, msg);
		}catch(Exception e) {
			System.out.print("Logging error");
		}
	}
}
