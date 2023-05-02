package com.met.cdac.AlgoTrading1.model;


import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Login {
	
	@Id  @NotNull
	private String loginPhoneNO;
	
	
	 @NotEmpty  @NotNull
		@Size(min=3 , max=10 , message="Password must be min of 3 Characters and Max of 10 Characters")
		 @Pattern(regexp= "^([A-Za-z0-9]){1,}[@#]([A-Za-z0-9]+)?[A-Za-z0-9]$" )
		private String loginPassword;
	

	public String getLoginPhoneNO() {
		return loginPhoneNO;
	}


	public void setLoginPhoneNO(String loginPhoneNO) {
		this.loginPhoneNO = loginPhoneNO;
	}





	public String getLoginPassword() {
		return loginPassword;
	}





	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}





	@Override
	public String toString() {
		return "Login [loginPhoneNO=" + loginPhoneNO + ", loginPassword=" + loginPassword + "]";
	}


 

	
	
	

}
