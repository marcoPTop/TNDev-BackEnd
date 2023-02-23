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
	
	public User(String nome, String cognome, String cf, int eta, Account account) {
		this.name = nome;
		this.surname = cognome;
		this.taxCode = cf;
		this.years = eta;
		this.account = account;
	}

	public int getIdUtente() {
		return idUser;
	}

	public void setIdUtente(int idUtente) {
		this.idUser = idUtente;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	public String getCognome() {
		return surname;
	}

	public void setCognome(String cognome) {
		this.surname = cognome;
	}

	public String getCf() {
		return taxCode;
	}

	public void setCf(String cf) {
		this.taxCode = cf;
	}

	public int getEta() {
		return years;
	}

	public void setEta(int eta) {
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
		return "Utente [idUtente=" + idUser + ", nome=" + name + ", cognome=" + surname + ", cf=" + taxCode + ", eta="
				+ years + ", account=" + account + "]";
	}
}
