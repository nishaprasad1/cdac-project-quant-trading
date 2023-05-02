package com.met.cdac.AlgoTrading1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Contect {
	
	
	@Column(name="uname")
	private String name;
	@Id
	private String email;
	
	private String message;
	@Override
	public String toString() {
		return "Contect [name=" + name + ", email=" + email + ", message=" + message + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
