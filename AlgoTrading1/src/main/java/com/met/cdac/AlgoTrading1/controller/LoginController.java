package com.met.cdac.AlgoTrading1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.met.cdac.AlgoTrading1.model.Registeration;
import com.met.cdac.AlgoTrading1.service.LoginService;
import com.met.cdac.AlgoTrading1.model.*;



@CrossOrigin(origins="*")
@RestController
@RequestMapping("/Login")

public class LoginController {
	
	
	@Autowired
	
	private LoginService loginService;
	
	@PostMapping("/")
	public ResponseEntity<Registeration> Login (  @RequestBody Login loginObject)
	{
		System.out.println("XXXX"+loginObject);
		System.out.println("****************************");
		//Registeration createdCustomer =registerationService.createCustomer(registeration);
		//return null;
		
		Registeration registredCustomer=	loginService.loginVelidation(loginObject);
	  System.out.println("object passing to react" + " "+registredCustomer);
	   return new ResponseEntity<>(registredCustomer, HttpStatus.OK);
	}
	
	

}