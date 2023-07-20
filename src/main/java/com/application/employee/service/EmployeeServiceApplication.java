package com.application.employee.service;

import com.application.employee.service.auth.AuthenticationService;
import com.application.employee.service.auth.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.application.employee.service.user.Role.ADMIN;
import static com.application.employee.service.user.Role.MANAGER;


@SpringBootApplication
public class EmployeeServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EmployeeServiceApplication.class, args);
	}

}
