package com.example.Saceva2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Saceva2.Bo.Utente;

public interface InterfaceUtenteJpa extends JpaRepository<Utente, Integer>{
	
}
