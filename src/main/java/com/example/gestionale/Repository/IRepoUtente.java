package com.example.gestionale.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionale.Bo.Utente;

public interface IRepoUtente extends JpaRepository<Utente,Integer>{

	public Utente findById(int id);
	
}
