package com.halfacode.spring_real_time_learning;

import com.halfacode.spring_real_time_learning.service.DataSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringRealTimeLearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRealTimeLearningApplication.class, args);
	}
	@Bean
	CommandLineRunner run(DataSeeder seeder) {
		return args -> seeder.seed();
	}
}
