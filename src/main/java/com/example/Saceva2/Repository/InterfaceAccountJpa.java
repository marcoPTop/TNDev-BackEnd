package com.example.Saceva2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Saceva2.Bo.Account;

public interface InterfaceAccountJpa extends JpaRepository<Account, Integer> {
	
}
