package com.example.Saceva2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Saceva2.Bo.TabConfig;

public interface IRepoTabConfig extends JpaRepository<TabConfig, Integer>{
	
	public TabConfig findById(int idTabConfig);
	
	public TabConfig findByKey(String keyTabConfig);

}
