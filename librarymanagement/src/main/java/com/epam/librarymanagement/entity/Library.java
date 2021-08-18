package com.epam.librarymanagement.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Library {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String userName;
	int bookId;

}
