package com.training.ediary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.training.ediary.domain"})
public class EdiaryApplication {

	public static void main(String[] args) {

		SpringApplication.run(EdiaryApplication.class, args);
	}

}