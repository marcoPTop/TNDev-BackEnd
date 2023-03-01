package com.example.Saceva2.Bo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "User")
public class User {

	@Id
	@SequenceGenerator(name = "SEQ_IDUtente", sequenceName = "SEQ_IDUtente", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IDUtente")
	@Column(name = "Id", unique = true, updatable = false)
	private int idUser;
	@Column(length = 30, nullable = false)
	private String name;
	@Column(length = 30, nullable = false)
	private String surname;
	@Column(length = 16, nullable = false)
	private String taxCode;
	@Column(length = 2, nullable = false)
	private int years;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idAccount", referencedColumnName = "Id")
	private Account account;
	
	public User() {}
	
	public User(String name, String surname, String taxCode, int years, Account account) {
		this.name = name;
		this.surname = surname;
		this.taxCode = taxCode;
		this.years = years;
		this.account = account;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUtente) {
		this.idUser = idUtente;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String cognome) {
		this.surname = cognome;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String cf) {
		this.taxCode = cf;
	}

	public int getYears() {
		return years;
	}

	public void setYears(int eta) {
		this.years = eta;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", name=" + name + ", surname=" + surname + ", taxCode=" + taxCode
				+ ", years=" + years + ", account=" + account + "]";
	}

}
