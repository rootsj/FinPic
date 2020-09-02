package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"controller", "payroll"})
public class Step01BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(Step01BasicApplication.class, args);
	}

}
