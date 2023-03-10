package com.example.Saceva2.Repository;

import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.Saceva2.Bo.User;

@EnableJpaRepositories
@EnableJdbcRepositories//queste due annotazioni sembra che servano per non far andar in conflitto quando lancio in jar stand alone
public interface IRepoUser extends JpaRepository<User, Integer>{
	
	public User findById(int idUtente);
	
	public User findByTaxCode(String taxCode);
	
//	public User findByNameSurname(String name, String surname);
	
}
