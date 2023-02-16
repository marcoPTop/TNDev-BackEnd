package com.example.Saceva2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Saceva2.Bo.Utente;

//@EnableJpaRepositories
//@EnableJdbcRepositories//queste due annotazioni sembra che servano per non far andar in conflitto quando lancio in jar stand alone
public interface IRepoUtente extends JpaRepository<Utente, Integer>{
	
	public Utente findById(int idUtente);
	
}
