package com.met.cdac.AlgoTrading1.Repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.met.cdac.AlgoTrading1.model.Signal;



@Repository
public class SignalRepo {
	




	
		@Autowired
		private SessionFactory sessionFactory;
		
		   public  List<Signal> getAllSignals ()
		   {
			   Session session = null;
				Transaction tx = null;
				List<Signal> list = null;
				
				try
				{
					session=sessionFactory.openSession();
					
					 tx =session.beginTransaction();
					
					 System.out.println(" befor q");
					 Query<Signal> query= session.createQuery("From com.met.Algotrading.model.Signal  order by signalId desc " , Signal.class);
					  list = query.getResultList();
					  
					//  String sql = "FROM com.met.Algotrading.model.Subscription where phoneNO=:nameVal";   //HQL
					//	 Query<Subscription> query= session.createQuery(sql , Subscription.class);
					  
					//  Query<Account> createQuery = session.createQuery("from com.met.model.Account order by balance desc", Account.class);
				    //List<Account> list = createQuery.list();
						
						 
				    System.out.println(" after q");
				       System.out.println(list);
					
				}catch(Exception e)
				{
					
					if(session!= null)
				    	session.close();
					
				}
				
				
				
				return list;
			    
		   }
		
	}


