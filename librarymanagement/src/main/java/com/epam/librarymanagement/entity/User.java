package com.epam.librarymanagement.entity;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class User {

	private String userName;
	private String email;
	private String name;
	private List<Book> books;

}
