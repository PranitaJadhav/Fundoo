package com.bridgelabz.demo.exception;

import org.springframework.stereotype.Component;

@Component
public class UserNotPresent extends Exception {

	private static final long serialVersionUID = 1L;

	public UserNotPresent(String message) {
		super(message);

	}

	public UserNotPresent() {
		super();
	}

}
