package com.schoollog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })*/
/*@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })*/
public class SchoolLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolLogApplication.class, args);
	}

}
