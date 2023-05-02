package com.met.cdac.AlgoTrading1.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.met.cdac.AlgoTrading1.Repository.SignalRepo;
import com.met.cdac.AlgoTrading1.model.Signal;


@Service
public class SignalService {

	@Autowired
	private SignalRepo signalRepo;

	public List<Signal> getAllSignals() {
		
		List<Signal> signal=signalRepo.getAllSignals();
		
		return signal;

	}



}
