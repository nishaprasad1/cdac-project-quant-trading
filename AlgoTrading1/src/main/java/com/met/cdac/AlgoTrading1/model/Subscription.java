package com.met.cdac.AlgoTrading1.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;


@Entity
public class Subscription {
	
	
	

	@Id @NotNull
	 @GeneratedValue
	private int sub_Id;
	
	@NotNull
	private  String phoneNO;
	
	@NotNull
	private  String strategyName;
	
	@NotNull	
	private java.sql.Date startDate;
	                                
	@NotNull
	private  java.sql.Date endDate;
	
	@NotNull
	private int totalPayment;

	public int getSub_Id() {
		return sub_Id;
	}

	public void setSub_Id(int sub_Id) {
		this.sub_Id = sub_Id;
	}

	public String getPhoneNO() {
		return phoneNO;
	}

	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public java.sql.Date getStartDate( ) {
		return startDate;
	}

	public void setStartDate(long millis) {
		  java.sql.Date date=new java.sql.Date(millis);  
		this.startDate = date;
	}

	public java.sql.Date getEndDate() {
		return endDate;
	}

	public void setEndDate( long millis) {
		
		java.sql.Date date=new java.sql.Date(millis);
		this.endDate = date;
	}

	public int getTotalPayment() {
		return totalPayment;
	}

	public void setTotalPayment(int totalPayment) {
		this.totalPayment = totalPayment;
	}

	@Override
	public String toString() {
		return "Subscription [sub_Id=" + sub_Id + ", phoneNO=" + phoneNO + ", strategyName=" + strategyName
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", totalPayment=" + totalPayment + "]";
	}
	

}
