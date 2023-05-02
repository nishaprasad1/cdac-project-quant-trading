package com.met.cdac.AlgoTrading1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.met.cdac.AlgoTrading1.model.Registeration;



public interface RegisterationRepo extends JpaRepository<Registeration, String>
{
	// we are making our repository to Jpa repository

}
