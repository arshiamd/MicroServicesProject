package com.epam.bookservice;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookserviceApplication {
	
	static Logger logger = Logger.getLogger(BookserviceApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(BookserviceApplication.class, args);
	}

}
