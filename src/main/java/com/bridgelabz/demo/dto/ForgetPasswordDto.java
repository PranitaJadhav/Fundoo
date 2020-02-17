package com.bridgelabz.demo.dto;

public class ForgetPasswordDto {
	
	private String  emailid;

	
	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Override
	public String toString() {
		return "ForgetPasswordDto [ emailid=" + emailid + "]";
	}
	

}
