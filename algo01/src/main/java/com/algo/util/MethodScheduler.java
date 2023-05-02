package com.algo.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MethodScheduler {

	private Timer timer;
	private MyLogger logger;
	private StrategyORB strategy;
	
	public MethodScheduler(StrategyORB strategy) {
		this.strategy = strategy;
		logger = MyLogger.getMyLogger();
		
		
	}
	public void schedule() {
		//setup timer
		timer = new Timer();
		TimerTask timertask = new TimerTask() {
		@Override
		public void run() {
			logger.logEntry("IT'S 3:40. Shutting down Algo.");
			strategy.saveToDB();
			}
		};
				
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY,15);
		c.set(Calendar.MINUTE, 40);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);

		if(c.getTime().before(new Date())) {
			c.add(Calendar.DATE, 1);
		}
				
		//register timer
		timer.schedule(timertask, c.getTime());
	}
	
}
