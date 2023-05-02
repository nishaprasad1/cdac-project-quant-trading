package algo01;
import com.algo.model.InstrumentInfo;
import com.algo.model.InstrumentOHLC;
import com.algo.util.*;
import java.util.*;
import com.zerodhatech.kiteconnect.KiteConnect;

import dao.DbAccess;

public class Program {
	
	public static KiteConnect kc;
	public static boolean doExit=false;
	public static boolean isStrategyRunning=false;
	

	public static void main(String[] args) {
		//System.out.println("Started");
		String apiKey="o2j0k7vnhkv1hro2"; 
		String userId="GY4644";
		String reqToken="6rWKMCvkq93iEBZQRdZIdebOqkLuomjo";
		String apiSecret="zl9m0fkpaomj0z9gst9cb6lp2o1rl4er";
		String prevDate;
		MyLogger logger = MyLogger.getMyLogger();
		
		logger.logEntry("Started Code");
		
		//Prepare instrument token list to monitor
		Map<Long,InstrumentOHLC> instOHLC = new HashMap<Long,InstrumentOHLC>();
		//get company list from DB
		DbAccess dbAccess = new DbAccess();
		List companyList = dbAccess.getInstruments();
		//check if we got list
		if(companyList==null) {
			System.out.println("Cannot get data from DB: Program Terminating.");
			logger.logEntry("Cannot get data from DB: Program Terminating.");
			return;
		}
		
		//prepare Sub data
		for(Object e:companyList) {
			//type cast 
			InstrumentInfo instInfo = (InstrumentInfo)e;
			//make new object
			InstrumentOHLC inst = new InstrumentOHLC();
			//set values
			inst.setInstrumentTokenNo(instInfo.getTokenNo());
			inst.setInstrumentName(instInfo.getCompanyName());
			//insert this object into MAP
			instOHLC.put(inst.getInstrumentTokenNo(),inst);
		}
		
		//connecting to KiteAPI
		kc = CustomConnection.tryConnection(apiKey,userId,reqToken,apiSecret);
		if(kc==null) {
			//If not connected : Exit
			System.out.println("ERROR in connection.");
			logger.logEntry("Error in KC connection.");
			return;
		}
		logger.logEntry("KC registered successfully.");
		//STD in
		Scanner in = new Scanner(System.in);
		System.out.print("Enter Prev date dd/MM/yyyy:");
		prevDate = in.nextLine();
		logger.logEntry("Prev Date as "+prevDate);
		
		//if connected; Prepare ORB Strategy with INFY stock
		StrategyORB s1 = new StrategyORB(instOHLC,prevDate);
		
		//Schedule method
		MethodScheduler methodScheduler = new MethodScheduler(s1);
		methodScheduler.schedule();
		
		do {
			//logger.logEntry("Started");
			System.out.print("Enter your command :");
			
			//get string from user
			String cmd = in.nextLine();
			cmd = cmd.toUpperCase();
			
			switch(cmd) {
			
				case "RUN":
					logger.logEntry("CMD : RUN");
					if(!isStrategyRunning) {
						System.out.println("Starting...");
						//s1.startAlgo();
						s1.start();
						isStrategyRunning=true;
					}else {
						System.out.println("Already started.");
					}
				break;
				case "UPDATE_DB":	//at 3:30
					logger.logEntry("CMD : UPDATE_DB");
					s1.saveToDB();
				break;
				case "LOAD_OHLC":	//at 9:30
					logger.logEntry("CMD : LOAD_OHLC");
					s1.loadPrevOHLC();
				break;
				case "STATUS":
					logger.logEntry("CMD : STATUS");
					System.out.println("Ticker Provider :"+s1.isWebSocketOpen);
					System.out.println("Strategy running :"+isStrategyRunning);
					System.out.println("Exit flag :"+doExit);
				break;
				case "SHOW_SUB_DATA":
					logger.logEntry("CMD : SHOW_SUB_DATA");
					s1.showOHLC();
				break;
				case "EXIT":
					logger.logEntry("CMD : EXIT");
					doExit=true;
				break;
				default:
					System.out.println("command ERROR");
				break;
			}
		}while(!doExit);
		
		in.close();
		System.out.println("Program Exited:");
		logger.logEntry("Program Exiting");
		
	}

}
