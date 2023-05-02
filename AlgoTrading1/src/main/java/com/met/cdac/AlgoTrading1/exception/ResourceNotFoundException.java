package com.met.cdac.AlgoTrading1.exception;

public class ResourceNotFoundException extends RuntimeException
{
	String resourceName;
	String feildName;
	String phoneNO;
	                              
	public ResourceNotFoundException(String resourceName ,String feildName,String phoneNO)
	{
		super(String.format("%s not found with %s :%s",resourceName,feildName, phoneNO));
		
		this.resourceName=resourceName;
		this.feildName=feildName;
		this.phoneNO=phoneNO;
		
	}
	

}