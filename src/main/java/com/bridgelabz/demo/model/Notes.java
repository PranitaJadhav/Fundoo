package com.bridgelabz.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Notes implements Serializable{
	@Id
	private int nid;
	private String title;
	private String description;
	
	  @JoinColumn(name = "Id")
	  
	  @ManyToOne
	 
	private UserInfo user;
	

	

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

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}
	
	/*
	 * @Override public String toString() { return "Notes [nid=" + nid + ", title="
	 * + title + ", description=" + description + ", user=" + user + "]"; }
	 */
	
	
	

}
