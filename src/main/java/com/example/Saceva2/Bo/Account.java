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
	@Column(name = "Password", nullable = false, length = 120)
	private String pass;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idRole", referencedColumnName = "id")
	private Role role;
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "account")
	@JsonIgnore
	private User users;
	
	public Account() {}
	
	public Account(String uName, String email, String pass, Role role, User user) {
		this.uName = uName;
		this.email = email;
		this.pass = pass;
		this.role = role;
		this.users = user;
	}

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return users;
	}

	public void setUser(User utente) {
		this.users = utente;
	}

	@Override
	public String toString() {
		return "Account [idAccount=" + idAccount + ", uName=" + uName + ", email=" + email + ", pass=" + pass
				+ ", role=" + role + ", users=" + users + "]";
	}

}
