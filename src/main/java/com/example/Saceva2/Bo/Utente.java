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
@Table(name = "Utenti")
public class Utente {

	@Id
	@SequenceGenerator(name = "SEQ_IDUtente", sequenceName = "SEQ_IDUtente", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IDUtente")
	@Column(name = "Id", unique = true, updatable = false)
	private int idUtente;
	@Column(length = 30, nullable = false)
	private String nome;
	@Column(length = 30, nullable = false)
	private String cognome;
	@Column(length = 16, nullable = false)
	private String cf;
	@Column(length = 2, nullable = false)
	private int eta;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "idAccount", referencedColumnName = "Id")
	private Account account;
	
	public Utente(String nome, String cognome, String cf, int eta, Account account) {
		this.nome = nome;
		this.cognome = cognome;
		this.cf = cf;
		this.eta = eta;
		this.account = account;
	}

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Utente [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome + ", cf=" + cf + ", eta="
				+ eta + ", account=" + account + "]";
	}
}
