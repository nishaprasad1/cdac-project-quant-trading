package com.algo.model;


import jakarta.persistence.*;

@Entity
@Table(name="orb_tbl")
public class InstrumentOHLC {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name="token")
	long instrumentTokenNo;
	
	@Column(name="comp_name")
	String instrumentName;
	
	@Column(name="open_price")
	double open;
	 
	@Column(name="high_price")
	double high;
	
	@Column(name="low_price")
	double low;
	
	@Column(name="close_price")
	double close;
	
	@Column(name="record_date")
	java.sql.Date recordDate;
	
	transient boolean scan=false;
	
	public InstrumentOHLC() {
		super();
		java.util.Date d = new java.util.Date();
		recordDate = new java.sql.Date(d.getTime());
	}
	
	public boolean getScan() {
		return scan;
	}
	public void setScan(boolean scan) {
		this.scan = scan;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getOpen() {
		return open;
	}
	public void setOpen(double open) {
		this.open = open;
	}
	

	public double getHigh() {
		return high;
	}
	public void setHigh(double high) {
		this.high = high;
	}
	public double getLow() {
		return low;
	}
	public void setLow(double low) {
		this.low = low;
	}
	public double getClose() {
		return close;
	}
	public void setClose(double close) {
		this.close = close;
	}
	public Long getInstrumentTokenNo() {
		return instrumentTokenNo;
	}
	public void setInstrumentTokenNo(long instrumentTokenNo) {
		this.instrumentTokenNo = instrumentTokenNo;
	}
	public String getInstrumentName() {
		return instrumentName;
	}
	public void setInstrumentName(String instrumentName) {
		this.instrumentName = instrumentName;
	}
	
	
	@Override
	public String toString() {
		return "InstrumentOHLC [id=" + id + ", instrumentTokenNo=" + instrumentTokenNo + ", instrumentName="
				+ instrumentName + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close
				+ ", recordDate=" + recordDate + ", scan=" + scan + "]";
	}
}
