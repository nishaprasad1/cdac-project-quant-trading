package com.met.cdac.AlgoTrading1.model;








import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Registeration {
	

	@Override
	public String toString() {
		return "Registeration [phoneNO=" + phoneNO + ", name=" + name + ", email=" + email + ", password=" + password
				+  "]";
	}
	
	@Id  @NotNull
	private String phoneNO;

	@NotEmpty @NotNull
	@Size(min=4 , message="username must be min 4 character")
	private String name;
	
	
	@NotNull @NotEmpty @Email(message="Email address is not valid !!")
	private String email;
	 
	@NotEmpty  @NotNull
	@Size(min=3 , max=10 , message="Password must be min of 3 Characters and Max of 10 Characters")
	 @Pattern(regexp= "^([A-Za-z0-9]){1,}[@#]([A-Za-z0-9]+)?[A-Za-z0-9]$" )
	private String password;
	
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNO() {
		return phoneNO;
	}
	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
