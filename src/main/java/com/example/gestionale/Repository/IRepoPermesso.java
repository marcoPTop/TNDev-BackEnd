package com.example.gestionale.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gestionale.Bo.Permesso;

public interface IRepoPermesso extends JpaRepository<Permesso,Integer>{

	public Permesso findById(int id);
	
}
