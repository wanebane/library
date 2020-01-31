package com.rivaldy.shki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShkiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShkiApplication.class, args);
	}
	
}
