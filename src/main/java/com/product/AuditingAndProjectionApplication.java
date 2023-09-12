package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AuditingAndProjectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuditingAndProjectionApplication.class, args);
	}

}
