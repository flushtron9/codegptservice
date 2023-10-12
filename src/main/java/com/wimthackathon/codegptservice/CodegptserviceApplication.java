package com.wimthackathon.codegptservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@SpringBootApplication
public class CodegptserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodegptserviceApplication.class, args);
	}

}
