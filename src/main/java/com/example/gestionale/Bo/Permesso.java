package com.example.gestionale.Bo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Ruoli")
public class Permesso {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(length=30,nullable=false,unique=true)
	private String ruolo;
	@OneToMany(fetch=FetchType.LAZY, mappedBy="ruolo")
	private List<Account> listAccount;/*se non ho capito male il mappedBy prende una variabile da un altra classe ; mi spiego
	meglio , in pratica il nome che deve andare nel mappedBy è il nome di un attributo che ha come tipo lo stesso della classe
	in cui vi è il mappedBy per far si di collegare un oggetto di tipo ruolo con un oggetto di tipo account , in questo caso 
	un solo ruolo fa parte di molti account OneToMany riga 22*/

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public List<Account> getListAccount() {
		return listAccount;
	}
	public void setListAccount(List<Account> listAccount) {
		this.listAccount = listAccount;
	}

}
