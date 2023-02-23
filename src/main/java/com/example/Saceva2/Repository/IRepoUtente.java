package com.example.Saceva2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Saceva2.Bo.User;

//@EnableJpaRepositories
//@EnableJdbcRepositories//queste due annotazioni sembra che servano per non far andar in conflitto quando lancio in jar stand alone
public interface IRepoUtente extends JpaRepository<User, Integer>{
	
	public User findById(int idUtente);
	
}
