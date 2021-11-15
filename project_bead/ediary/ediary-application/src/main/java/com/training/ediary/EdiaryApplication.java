package com.training.ediary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.training.ediary.application.service.TestDataGenerator;

@SpringBootApplication
public class EdiaryApplication {
	
	@Autowired
	private TestDataGenerator testDataGenerator;

	public static void main(String[] args) {

		SpringApplication.run(EdiaryApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) { 
        return args -> {
			testDataGenerator.createTestData();
        }; 
    } 
}