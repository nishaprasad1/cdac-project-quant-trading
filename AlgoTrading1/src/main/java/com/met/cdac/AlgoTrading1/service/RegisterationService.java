package com.met.cdac.AlgoTrading1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.met.cdac.AlgoTrading1.Repository.RegisterationRepo;
import com.met.cdac.AlgoTrading1.exception.ResourceNotFoundException;
import com.met.cdac.AlgoTrading1.model.Registeration;



@Service
public class RegisterationService {
	
	@Autowired
	private RegisterationRepo registerationRepo;
	
	//add reg
	 
	public Registeration createCustomer (Registeration registeration)
	{
		System.out.println(registeration);
		
			Registeration registered=registerationRepo.save(registeration);
			return registered;
   }
	
	
	public Registeration  updateRegisteration(Registeration registeration , String phoneNo )
	{
		Registeration registredCustomer =registerationRepo.findById(phoneNo).orElseThrow(()-> new ResourceNotFoundException("Customer", "phoneNO", registeration.getPhoneNO() ));
		registredCustomer.setName(registeration.getName());
		
		
		
		registredCustomer.setEmail(registeration.getEmail());
		Registeration updatedRegisteration=registerationRepo.save(registredCustomer);
		
		return updatedRegisteration;
	}
	
	public Registeration  RegisteredCustomerDeatils (String phoneNo)
	{
		
		Registeration registredCustomer = registerationRepo.findById(phoneNo).orElseThrow(()->new ResourceNotFoundException("Customer not found with ", "phoneNO",phoneNo));
		
		return registredCustomer;
		
	}



	
	 
	// 

}
