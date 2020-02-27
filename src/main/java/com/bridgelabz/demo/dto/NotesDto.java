package com.bridgelabz.demo.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.bridgelabz.demo.model.User;

public class NotesDto {
	@NotBlank(message = "title is mandatory")
    @Pattern(regexp = "^[a-z0-9_-]{2,20}$",message = "Enter valid title")
	private String title;
	private String description;
	User userInfo;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public User getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
	@Override
	public String toString() {
		return "NotesDto [title=" + title + ", description=" + description + ", userInfo=" + userInfo + "]";
	}
	

}
