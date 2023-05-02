package com.met.cdac.AlgoTrading1.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.met.cdac.AlgoTrading1.model.Subscription;
import com.met.cdac.AlgoTrading1.service.SubscriptionService;


@CrossOrigin(origins="*")
@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
	
	@Autowired
	private SubscriptionService subcriptionService;
	
 //subscription/{phoneNo}
	
	@GetMapping("/{phoneNo}")
	public ResponseEntity<List<Subscription>> getSubscription ( @PathVariable String phoneNo)
	{
		System.out.println(" subscriptin controller" +" " +phoneNo );
		List<Subscription> allSubscriptedStrategies =subcriptionService.getAllSubscriptionStrategies(phoneNo);
		
		return  ResponseEntity.ok(allSubscriptedStrategies);

	}
	
	// for craeting new Subscriber
	@PostMapping("/")
	public ResponseEntity<Subscription> createSubscrition  (@RequestBody Subscription subscription)
	{
		System.out.println("creating subscription");
		subcriptionService.createSubscription(subscription);
		return  ResponseEntity.ok(subscription);
	    	}
	
	

}
