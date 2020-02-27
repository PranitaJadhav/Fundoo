package com.bridgelabz.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotPresent extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotPresent(String message) {
		super(message);

	}

	
}
