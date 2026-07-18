package com.example.api.student_info;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {

		// It starts your Spring Boot application and starts the embedded web
		// server (Tomcat by default).
		SpringApplication.run(ApiApplication.class, args);
	}
}