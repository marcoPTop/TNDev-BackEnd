package com.example.Saceva2.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement(name = "DIPENDENTE")
@XmlAccessorType(XmlAccessType.NONE)
@JsonPropertyOrder({ "email", "userName", "pass", "name", "surname", "taxCode", "years", "role" })
public class Dependent {
	
	@XmlElement(name = "EMAIL")
	private String email;
	@XmlElement(name = "USER_NAME")
	private String uName;
	@XmlElement(name = "PASSWORD")
	private String pass;
	@XmlElement(name = "NAME")
	private String name;
	@XmlElement(name = "SURNAME")
	private String surname;
	@XmlElement(name = "TAX_CODE")
	private String taxCode;
	@XmlElement(name = "YEARS")
	private int years;
	@XmlElement(name = "RULE")
	private String role;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return uName;
	}
	public void setUserName(String userName) {
		this.uName = userName;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
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
	public String getRuolo() {
		return role;
	}
	public void setRuolo(String ruolo) {
		this.role = ruolo;
	}
	
	@Override
	public String toString() {
		return "Dependent [email=" + email + ", uName=" + uName + ", pass=" + pass + ", name=" + name + ", surname="
				+ surname + ", taxCode=" + taxCode + ", years=" + years + ", role=" + role + "]";
	}

}
