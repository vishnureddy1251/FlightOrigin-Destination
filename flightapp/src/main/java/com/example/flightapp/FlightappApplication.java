package com.example.flightapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.flightapp")
public class FlightappApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightappApplication.class, args);
	}

}
