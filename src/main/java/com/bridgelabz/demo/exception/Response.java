package com.bridgelabz.demo.exception;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

public class Response {
	private int status;
	private String message;
	private Object result;
//	private List<String> details;

	

	public Response(int status, String message, Object result) {
		super();
		this.status = status;
		this.message = message;
		this.result = result;
	//	this.details = details;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public Object getResult() {
		return result;
	}



	public void setResult(Object result) {
		this.result = result;
	}
	
}
