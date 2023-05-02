package com.met.cdac.AlgoTrading1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.met.cdac.AlgoTrading1.Repository.RegisterationRepo;
import com.met.cdac.AlgoTrading1.exception.ResourceNotFoundException;
import com.met.cdac.AlgoTrading1.model.Login;
import com.met.cdac.AlgoTrading1.model.Registeration;




@Service
public class LoginService {
	
	@Autowired
	private RegisterationRepo registerationRepo;
	
	public Registeration loginVelidation(Login login)
	{
		
		Registeration registredCustomer =registerationRepo.findById(login.getLoginPhoneNO()).orElseThrow(()-> new ResourceNotFoundException("Customer not found with ", "phoneNO", login.getLoginPhoneNO()));
		
		
		if(registredCustomer != null)
		  System.out.println("serice layer validation "+ " "+ registredCustomer);
		
		return registredCustomer;
		
	}
	
	

}
