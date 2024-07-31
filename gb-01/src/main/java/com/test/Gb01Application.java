package com.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.test","com.te"})
public class Gb01Application {

	public static void main(String[] args) {
		SpringApplication.run(Gb01Application.class, args);
	}

}
