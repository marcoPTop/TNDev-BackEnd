package com.example.Saceva2.SpringScheduler.Dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@XmlRootElement(name = "DIPENDENTE")
@XmlAccessorType(XmlAccessType.NONE)
@JsonPropertyOrder({ "email", "userName", "pass", "name", "surname", "cf", "eta", "ruolo" })
public class Dipendente {
	
	@XmlElement(name = "EMAIL")
	private String email;
	@XmlElement(name = "USER_NAME")
	private String userName;
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
	private String ruolo;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	@Override
	public String toString() {
		return "Dipendente [email=" + email + ", userName=" + userName + ", pass=" + pass + ", name=" + name
				+ ", surname=" + surname + ", cf=" + taxCode + ", eta=" + years + ", ruolo=" + ruolo + "]";
	}
	
}
