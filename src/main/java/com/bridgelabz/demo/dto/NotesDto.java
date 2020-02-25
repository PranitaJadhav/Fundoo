package com.bridgelabz.demo.dto;

import com.bridgelabz.demo.model.User;

public class NotesDto {
	
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
