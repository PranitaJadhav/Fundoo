package com.bridgelabz.demo.dto;

public class UserDto {
	private String id;
	private String name;

	private String emailid;
	private String mobileNo;
	
	private String password;
	private String confirmPassword;
	
	public UserDto() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		 this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	  @Override 
	  public String toString() { return "UserDto [id=" + id + ", Name=" +
	  name + ", emailid=" + emailid + ", mobileNo=" + mobileNo + ", password=" +
	  password + ", confirmPassword=" + confirmPassword + "]"; }
	 
	
	
	

}
