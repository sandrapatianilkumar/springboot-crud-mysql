package com.samplemysql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class SpringbootCrudMysqlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrudMysqlApplication.class, args);
	}

}
