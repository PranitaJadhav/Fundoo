package com.bridgelabz.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotBlank /* (message = "Name is mandatory") */
	//@Pattern(regexp = "^[a-z0-9_-]{2,20}$",message = "Enter valid username")
	@Size(min = 2,message = "First name should have minimum 2 characters")
	private String name;

	@NotBlank /* (message = "email is mandatory") */
	@Email(message = "Enter valid emailId")
	private String emailid;

	@NotBlank
	//@Pattern(regexp = "^[0][1-9]\\d{9}$|^[1-9]\\d{9}$",message = "enter valid mobile no")
	@Size(min = 10,max = 15, message = "not in range {min} &{max}")
	private String mobileNo;

	@NotBlank(message = "password is mandatory")
	private String password;

	@NotNull
	private String confirmPassword;
	
	private String image;

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", emailid=" + emailid + ", mobileNo=" + mobileNo + ", password="
				+ password + ", confirmPassword=" + confirmPassword + ", image=" + image + "]";
	}

	

	
}
