package com.met.cdac.AlgoTrading1.Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.met.cdac.AlgoTrading1.model.Subscription;
import com.met.cdac.AlgoTrading1.exception.*;






@Repository 
public class SubscriptionRepo{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	   public  List<Subscription> getAllSubscriptionStrategies (String phoneNo)
	   {
		   Session session = null;
			Transaction tx = null;
			List<Subscription> allSubsciption = null;
			
			try
			{
				 session =  sessionFactory.openSession();
				 tx =session.beginTransaction();
				 String sql = "FROM com.met.Algotrading.model.Subscription where phoneNO=:nameVal";   //HQL
				 Query<Subscription> query= session.createQuery(sql , Subscription.class);
				 
						 query.setParameter("nameVal", phoneNo);

		 allSubsciption	= query.getResultList();
			System.out.println("data in subscription repo");
			
			System.out.println(allSubsciption);
				
			}catch(Exception e)
			{
				//throw new ResourceNotFoundException("Customer", "phoneNO", phoneNo);
				if(session!= null)
			    	session.close();
				
			}
			return allSubsciption;
		  
		   
	   }
	   
	   public  Subscription createSubcription (Subscription subscription)
	   {

			 Session session = null;
				Transaction tx = null;
		  
		   
			try
			{
				
				session =  sessionFactory.openSession();
				 tx =session.beginTransaction();
				 session.save(subscription);
				 tx.commit();
				
			}catch(Exception e)
			{
				if(tx  != null) tx.rollback();
			 throw new ResourceNotFoundException("Registeration", "Required with ", subscription.getPhoneNO());
				
				
			}finally {
				if(session != null) session.close();
			}
			 return subscription;
				
			}

	
		   
		   
	   }


