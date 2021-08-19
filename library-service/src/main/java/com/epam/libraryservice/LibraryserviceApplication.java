package com.epam.libraryservice;

import lombok.Generated;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LibraryserviceApplication {
	@Generated
	public static void main(String[] args) {
		SpringApplication.run(LibraryserviceApplication.class, args);
	}

}
