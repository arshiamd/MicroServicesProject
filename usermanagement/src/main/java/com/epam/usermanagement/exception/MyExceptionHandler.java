package com.epam.usermanagement.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public Map<String,String> handleMethodArguments(MethodArgumentNotValidException exception, WebRequest webRequest){
		Map<String,String> errors = new HashMap<>();
		StringBuilder messages = new StringBuilder();
		exception.getBindingResult().getAllErrors().forEach(error->{
			messages.append(error.getDefaultMessage()).append("\n");
		});
		errors.put("time", new Date().toString());
		errors.put("status", HttpStatus.BAD_REQUEST.name());
		errors.put("error", messages.toString());
		errors.put("path", webRequest.getDescription(false));
		return errors;
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleBookNotFound(UserNotFoundException exception) {
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}

}

