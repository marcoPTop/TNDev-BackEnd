package com.example.Saceva2.Bo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "TAB_CONFIG")
public class TabConfig {
	
	@Id
	@SequenceGenerator(name = "SEQ_IDTabConfig", sequenceName = "SEQ_IDTabConfig", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_IDTabConfig")
	@Column(name = "Id")
	private int idTabConfig;
	@Column(name = "keyy")
	private String key;
	@Column(name = "valuee")
	private String value;
	
	public int getIdTabConfig() {
		return idTabConfig;
	}
	public void setIdTabConfig(int idTabConfig) {
		this.idTabConfig = idTabConfig;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "TabConfig [idTabConfig=" + idTabConfig + ", key=" + key + ", value=" + value + "]";
	}

}
