package com.kolayvergi.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.kolayvergi"})
@ComponentScan(basePackages = {"com.kolayvergi"})
@EnableJpaRepositories(basePackages = {"com.kolayvergi"})
@SpringBootApplication
public class KolayvergiBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KolayvergiBackendApplication.class, args);
	}

}
