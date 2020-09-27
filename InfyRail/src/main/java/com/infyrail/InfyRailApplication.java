package com.infyrail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:ValidationMessages.properties")
public class InfyRailApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfyRailApplication.class, args);
	}

}
