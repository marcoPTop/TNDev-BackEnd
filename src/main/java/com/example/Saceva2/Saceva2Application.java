package com.example.Saceva2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Saceva2Application {

	public static void main(String[] args) {
//		System.out.println("after loading springboot");
		SpringApplication.run(Saceva2Application.class, args);
//		System.out.println("before loading springboot");
	}

}
