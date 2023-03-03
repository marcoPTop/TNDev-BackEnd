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
@Table(name = "Role")
public class Role {

	@Id
	@SequenceGenerator(name = "SEQ_IDRole", sequenceName = "SEQ_IDRole", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IDRole")
	@Column(name = "Id")
	private int id;
	@Column(length = 30, nullable = false, unique = true)
	private String role;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
	@JsonIgnore
	private List<Account> listAccount;
	
	public Role() {}
	
	public Role(String ruolo) {
		this.role = ruolo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuolo() {
		return role;
	}

	public void setRuolo(String ruolo) {
		this.role = ruolo;
	}

	public List<Account> getListAccount() {
		return listAccount;
	}

	public void setListAccount(List<Account> listAccount) {
		this.listAccount = listAccount;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + ", listAccount=" + listAccount + "]";
	}

}
