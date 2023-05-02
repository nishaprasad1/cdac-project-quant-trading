package com.algo.model;

import java.sql.Date;


import jakarta.persistence.*;

@Entity
@Table(name="signals")
public class Signal {
	
	@Id
	@Column(name="signalId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int signalId;
	
	@Column(name="companyName" ,length = 50)
	
	String companyName;
	
	@Column(name="companyId")
	double companyId;
	
	@Column(name="strategyName" ,length = 50)
	String strategyName;
	
	@Column(name="generateDate")
	Date recordDate;
	
	@Column(name="buy")
	double buyPrice;
	
	@Column(name="sell")
	double sellPrice;
	
	@Column(name="signalType" ,length = 10 )
	String signalType;
	
	@Column(name="stopLoss")
	double stopLoss;

	public Signal() {
		super();
		java.util.Date javaDate = new java.util.Date();
		recordDate = new java.sql.Date(javaDate.getTime());
	}
	
	public int getSignalId() {
		return signalId;
	}

	public void setSignalId(int signalId) {
		this.signalId = signalId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public double getCompanyId() {
		return companyId;
	}

	public void setCompanyId(double companyId) {
		this.companyId = companyId;
	}

	public String getStrategyName() {
		return strategyName;
	}

	public void setStrategyName(String strategyName) {
		this.strategyName = strategyName;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getSignalType() {
		return signalType;
	}

	public void setSignalType(String signalType) {
		this.signalType = signalType;
	}

	public double getStopLoss() {
		return stopLoss;
	}

	public void setStopLoss(double stopLoss) {
		this.stopLoss = stopLoss;
	}

	@Override
	public String toString() {
		return "Signal [signalId=" + signalId + ", companyName=" + companyName + ", companyId=" + companyId
				+ ", strategyName=" + strategyName + ", recordDate=" + recordDate + ", buyPrice=" + buyPrice
				+ ", sellPrice=" + sellPrice + ", signalType=" + signalType + ", stopLoss=" + stopLoss + "]";
	}
	
	
	
}
