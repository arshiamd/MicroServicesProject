package com.epan.usermanagement.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class UserDTO {

	@NotNull(message = "Username cannot be null")
	private String userName;
	@Email
	private String email;
	@Size(min = 6, message = "Name should have minimum six characters")
	private String name;
	
}

