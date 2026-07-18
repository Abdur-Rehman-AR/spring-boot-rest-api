package com.example.api.student_list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunApplication {
    public static void main(String[] args) {

        // Starts the Spring Boot application and embedded Tomcat server
        SpringApplication.run(RunApplication.class, args);

    }
}