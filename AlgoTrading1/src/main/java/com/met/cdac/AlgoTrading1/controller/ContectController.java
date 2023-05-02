package com.met.cdac.AlgoTrading1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.met.cdac.AlgoTrading1.model.Contect;
import com.met.cdac.AlgoTrading1.service.ContectService;



@CrossOrigin(origins="*")
@RestController   //Controller
@RequestMapping("/contect")
public class ContectController {
	
	
	@Autowired
	  private ContectService contectService;
	
	@PostMapping("/")
	public ResponseEntity<Contect> createCustomer (  @RequestBody Contect contect)  //ResponseBody
	{
		//System.out.println("XXXX"+registeration);
		
		System.out.println("****************************");
		
		Contect contectt =contectService.createContect(contect);
		//return null;
		return new ResponseEntity<>(contectt, HttpStatus.CREATED);
	}

}
