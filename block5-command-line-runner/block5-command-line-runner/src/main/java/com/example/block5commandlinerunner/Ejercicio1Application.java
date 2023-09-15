package com.example.block5commandlinerunner;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Ejercicio1Application {

	@Bean
	CommandLineRunner ejecutame(){
		return p->
		{
			System.out.println("Linea a ejecutar cuando arranque");
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio1Application.class, args);
	}



}

