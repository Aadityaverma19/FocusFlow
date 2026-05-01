package com.focusflow.focusflow_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FocusflowBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FocusflowBackendApplication.class, args);
	}

}
