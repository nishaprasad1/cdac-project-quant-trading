package com.algo.util;

import com.zerodhatech.kiteconnect.*;
import com.zerodhatech.kiteconnect.kitehttp.*;
import com.zerodhatech.models.*;

public class CustomConnection {

	public static KiteConnect tryConnection(String apiKey, String userId, String reqToken, String apiSecret){
		
		try {
			
			KiteConnect kiteSdk = new KiteConnect(apiKey);
			kiteSdk.setUserId(userId);

			//String URL = kiteSdk.getLoginURL();
			User user =  kiteSdk.generateSession(reqToken, apiSecret);

			kiteSdk.setAccessToken(user.accessToken);
			kiteSdk.setPublicToken(user.publicToken);

			kiteSdk.setSessionExpiryHook(new SessionExpiryHook() {
			    //@Override
			    public void sessionExpired() {
			        System.out.println("Session Expired");                    
			    }
			});
			System.out.println("User Connected...");
			return kiteSdk;
		}catch(Exception e) {
			System.out.println("Connection Error : "+e);
			return null;
		}catch(Throwable t) {
			System.out.println("Connection Error : "+t);
			return null;
		}
		
	}
	
}
