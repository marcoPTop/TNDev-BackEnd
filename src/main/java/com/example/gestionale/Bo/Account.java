package com.example.gestionale.Bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Accounts")
public class Account {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAccount;
	@Column(length=30,unique=true)
	private String username;
	@Column(length=30,unique=true)
	private String email;
	@Column(length=30)
	private String password;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="id_ruolo",referencedColumnName="id")/*name sta per la colonna di collegamento in account
	con il valore del ruolo , mentre referencedColumnName sta per quale campo deve far fede il name , a chi è collegato
	in poche parole*/
	private Permesso ruolo;
	@OneToOne(fetch=FetchType.EAGER,mappedBy="account")
	private Utente utente;
	
	public int getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}
	public String getUserName() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPass(String password) {
		this.password = password;
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
		return "Account [idAccount=" + idAccount + ", userName=" + username + ", email=" + email + ", pass=" + password
				+ ", ruolo=" + ruolo + ", utente=" + utente + "]";
	}
	
}
