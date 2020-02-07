package com.bridgelabz.demo.dto;

import com.bridgelabz.demo.model.UserInfo;

public class NotesDto {
	private int nid;
	private String title;
	private String description;
	UserInfo userInfo;
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
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
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	/*
	 * @Override public String toString() { return "NotesDto [nid=" + nid +
	 * ", title=" + title + ", description=" + description + ", userInfo=" +
	 * userInfo + "]"; }
	 */

}
