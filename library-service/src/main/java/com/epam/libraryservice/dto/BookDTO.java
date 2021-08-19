package com.epam.libraryservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
	private Integer bookId;
	private String bookName;
	private String bookPublisher;
	private String bookAuthor;
}
