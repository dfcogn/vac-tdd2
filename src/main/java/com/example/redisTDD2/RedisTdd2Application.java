package com.example.redisTDD2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisTdd2Application {

	public static void main(String[] args) {
		SpringApplication.run(RedisTdd2Application.class, args);
		System.out.println("Start Redis...");
	}
}
