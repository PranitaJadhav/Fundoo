package com.bridgelabz.demo.dto;

public class ForgetPasswordDto {
	private String id;
	private String  emailid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	@Override
	public String toString() {
		return "ForgetPasswordDto [id=" + id + ", emailid=" + emailid + "]";
	}
	

}
