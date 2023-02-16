package com.example.Saceva2.Bo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ruoli")
public class Permesso {

	@Id
	@SequenceGenerator(name = "SEQ_IDRuolo", sequenceName = "SEQ_IDRuolo", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IDRuolo")
	@Column(name = "Id")
	private int id;
	@Column(length = 30, nullable = false, unique = true)
	private String ruolo;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ruolo")
	@JsonIgnore
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

	@Override
	public String toString() {
		return "Permesso [id=" + id + ", ruolo=" + ruolo + ", listAccount=" + listAccount + "]";
	}

}
