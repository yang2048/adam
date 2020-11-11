package com.yyovo.adam.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.yyovo.adam.**"})
@SpringBootApplication
public class AdamAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdamAdminApplication.class, args);
	}

}
