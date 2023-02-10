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
	private List<Account> listAccount;

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
