package com.example.Saceva2.Repository;

import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.Saceva2.Bo.Role;

@EnableJpaRepositories
@EnableJdbcRepositories//queste due annotazioni sembra che servano per non far andar in conflitto quando lancio in jar stand alone
public interface IRepoRole extends JpaRepository<Role, Integer> {
	
	public Role findById(int idPermesso);
	
	public Role findByRole(String role);
	
}
