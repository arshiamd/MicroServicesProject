package com.epam.bookmanagement.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
	
	@NotNull(message = "id cannot be null")
	private Integer bookId;
	@Size(min = 6, message = "Name should have minimum six characters")
	private String bookName;
	private String bookPublisher;
	private String bookAuthor;

}
