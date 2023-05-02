package com.met.cdac.AlgoTrading1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.met.cdac.AlgoTrading1.Repository.SubscriptionRepo;
import com.met.cdac.AlgoTrading1.model.Subscription;



@Service
public class SubscriptionService {

	@Autowired	
	private SubscriptionRepo subscriptionRepo;



	public List<Subscription> getAllSubscriptionStrategies( String phoneNo)
	{
		List<Subscription > allSubscription	 =subscriptionRepo.getAllSubscriptionStrategies(phoneNo);
		return allSubscription;
	}

	public Subscription createSubscription (Subscription subscription)
	{
		// System.out.println(subscription);
		long millis=System.currentTimeMillis(); 
		subscription.setStartDate(millis);

		subscription.setEndDate(millis+31560000000L);


		Subscription subscriptionDone	=subscriptionRepo.createSubcription(subscription);

		return subscriptionDone;
	} 

}
