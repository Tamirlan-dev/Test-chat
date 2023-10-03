package com.chat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.chat.repositories")
@ComponentScan(basePackages = "com.chat")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
