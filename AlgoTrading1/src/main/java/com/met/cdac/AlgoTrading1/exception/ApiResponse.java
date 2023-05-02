package com.met.cdac.AlgoTrading1.exception;

public class ApiResponse {
	
private String message;
  private 	Boolean bool;
	public ApiResponse(String message, Boolean bool) {
		super();
		this.message = message;
		this.bool = bool;
		
		
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getBool() {
		return bool;
	}
	public void setBool(Boolean bool) {
		this.bool = bool;
	}
	

}
