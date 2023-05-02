package com.met.cdac.AlgoTrading1.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.met.cdac.AlgoTrading1.model.Registeration;
import com.met.cdac.AlgoTrading1.service.RegisterationService;

import jakarta.validation.Valid;

@CrossOrigin(origins="*")
@RestController   //Controller
@RequestMapping("/registeration")
public class RegisterationController {


	@Autowired

	private RegisterationService registerationService;



	@PostMapping("/")
	public ResponseEntity<Registeration> createCustomer ( @Valid @RequestBody Registeration registeration)  //ResponseBody
	{
		System.out.println("XXXX"+registeration);
		
		System.out.println("****************************");
		
		Registeration createdCustomer =registerationService.createCustomer(registeration);
		//return null;
		return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
	}


	@PutMapping("/{RegisteredPhoneNO}/")
	public ResponseEntity<Registeration> updateCustomer ( @Valid @RequestBody Registeration registeration ,@PathVariable String phoneNo)
	{
		Registeration updatedCustomer=registerationService.updateRegisteration(registeration, phoneNo);
		return  ResponseEntity.ok(updatedCustomer);

	}
	
	
	@GetMapping("/{phoneNo}")
	public  ResponseEntity<Registeration> findRegisteredCustomerByMobileNo ( @PathVariable String phoneNo)
	{
		System.out.println("finding registered object");
		Registeration registeredCustomer=	registerationService.RegisteredCustomerDeatils(phoneNo);
		
		return  ResponseEntity.ok(registeredCustomer);
		
	}

}
