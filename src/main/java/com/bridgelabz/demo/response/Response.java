package com.bridgelabz.demo.response;

public class Response {
	private int status;
	private String message;
	private Object result;

	public Response(int status, String message, Object result) {
		super();
		this.status = status;
		this.message = message;
		this.result = result;
	}

	public Object getResult() {
		return result;

	}

	public String getMessage() {
		return message;
	}

	

	
	
}
