package com.example.Saceva2.Bo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Account {

	@Id
	@SequenceGenerator(name = "SEQ_IDAccount", sequenceName = "SEQ_IDAccount", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IDAccount")
	@Column(name = "Id")
	private int idAccount;
	@Column(name = "UserName", length = 30, nullable = false, unique = true)
	private String uName;
	@Column(length = 30, nullable = false, unique = true)
	private String email;
	@Column(name = "Password", nullable = false, length = 30)
	private String pass;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idRuolo", referencedColumnName = "id")
	private Permesso ruolo;
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "account")
	@JsonIgnore
	private Utente utente;

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Permesso getRuolo() {
		return ruolo;
	}

	public void setRuolo(Permesso ruolo) {
		this.ruolo = ruolo;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	@Override
	public String toString() {
		return "Account [idAccount=" + idAccount + ", uName=" + uName + ", email=" + email + ", pass=" + pass
				+ ", ruolo=" + ruolo + ", utente=" + utente + "]";
	}

}
