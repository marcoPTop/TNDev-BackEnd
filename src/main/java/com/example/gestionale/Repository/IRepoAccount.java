package com.example.gestionale.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionale.Bo.Account;

public interface IRepoAccount extends JpaRepository<Account,Integer>{

	public Account findById(int id);
	
}
