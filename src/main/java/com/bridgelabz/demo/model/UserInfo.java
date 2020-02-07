package com.bridgelabz.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class UserInfo implements Serializable{
	

	@Id
	@Column(name = "id")
	
	private int id;
	@Column(name = "name")

	private String name;
	
	@Column(name = "emailid")
	private String emailid;

	@Column(name = "mobile_no")
	private String mobileNo;

	@Column(name = "password")
	private String password;

	@Column(name = "confirm_password")
	private String confirmPassword;

	
	
	  public UserInfo() { super(); }
	 

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

	
	  @Override public String toString() { return "UserInfo [id=" + id + ", name="
	  + name + ", emailid=" + emailid + ", mobileNo=" + mobileNo + ", password=" +
	  password + ", confirmPassword=" + confirmPassword + "]"; }
	 

	
	
	

}
