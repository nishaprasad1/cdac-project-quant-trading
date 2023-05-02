package com.met.cdac.AlgoTrading1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.met.cdac.AlgoTrading1.model.Signal;
import com.met.cdac.AlgoTrading1.service.SignalService;



@CrossOrigin(origins="*")
@RestController
@RequestMapping("/signal")
public class SignalController {
	
	@Autowired
	private SignalService sigalService;
	
	@GetMapping("/")
	public ResponseEntity<List<Signal>> getAllSignals ()  //ResponseBody
	{
	
		
		List<Signal> signal =sigalService.getAllSignals();
		
		return  ResponseEntity.ok(signal);
	}

}
