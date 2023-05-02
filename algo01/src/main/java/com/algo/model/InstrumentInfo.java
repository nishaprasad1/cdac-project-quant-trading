package com.algo.model;

import jakarta.persistence.*;

@Entity
@Table(name="strategy_org")
public class InstrumentInfo {

	@Id
	@Column(name="company_token")
	Long tokenNo;
	
	@Column(name="company_name")
	String companyName;
	
	@Column(name="isEnabled")
	boolean isEnabled;

	public Long getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(Long tokenNo) {
		this.tokenNo = tokenNo;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "InstrumentInfo [tokenNo=" + tokenNo + ", companyName=" + companyName + "]";
	}
	
	
}
