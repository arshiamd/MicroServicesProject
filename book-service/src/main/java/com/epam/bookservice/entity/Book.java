package com.epam.bookservice.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Book {
	
	@Id
	private int bookId;
	private String bookName;
	private String bookPublisher;
	private String bookAuthor;
	private int port;
}
