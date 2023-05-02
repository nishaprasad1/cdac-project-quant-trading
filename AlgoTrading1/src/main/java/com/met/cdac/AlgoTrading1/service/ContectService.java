package com.met.cdac.AlgoTrading1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.met.cdac.AlgoTrading1.Repository.ContectRepo;
import com.met.cdac.AlgoTrading1.model.Contect;

@Service
public class ContectService {
	
	@Autowired
	private ContectRepo contectRepo;
	
	
	
//	public Registeration createCustomer (Registeration registeration)
//	{
//		System.out.println(registeration);
//		
//			Registeration registered=registerationRepo.save(registeration);
//			return registered;
//   }
     
	
	public Contect createContect (Contect contect)
	{
		
	  Contect contectt	=contectRepo.save(contect);
	  return contectt;
		
	}
}
