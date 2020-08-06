package com.br.print;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@SpringBootApplication
@EnableCircuitBreaker
public class ApiPrintLocalApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPrintLocalApplication.class, args);
	}

}
