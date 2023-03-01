package com.example.Saceva2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Saceva2.Bo.Account;

//@EnableJpaRepositories
//@EnableJdbcRepositories//queste due annotazioni sembra che servano per non far andar in conflitto quando lancio in jar stand alone
public interface IRepoAccount extends JpaRepository<Account, Integer> {
	
	public Account findById(int idAccount);
	
	public Account findByEmail(String email);
	
//	public Account findByUserName(String uName);
	
}
