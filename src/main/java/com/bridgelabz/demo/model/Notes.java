package com.bridgelabz.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Notes")

public class Notes implements Serializable {
	@Id
	@Column(name = "nid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int nid;

	@NotBlank
	@Column(name = "title")
	private String title;

	@NotNull
	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserInfo user;

	@ManyToMany
	@JoinTable ( name = "Label_Notes_join", joinColumns = @JoinColumn (name
			  ="nid"), inverseJoinColumns = @JoinColumn(name = "labelid"))
	private List<Label> listLabel = new ArrayList<Label>();
	//private List<Label> notesLabel;

	

	public int getNid() {
		return nid;
	}
	

	public Notes() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Notes(int nid, @NotBlank String title, @NotNull String description, UserInfo user, List<Label> listLabel) {
		super();
		this.nid = nid;
		this.title = title;
		this.description = description;
		this.user = user;
		this.listLabel = listLabel;
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

	public List<Label> getListLabel() {
		return listLabel;
	}

	public void setListLabel(List<Label> listLabel) {
		this.listLabel = listLabel;
	}


	@Override
	public String toString() {
		return "Notes [nid=" + nid + ", title=" + title + ", description=" + description + ", user=" + user
				+ ", listLabel=" + listLabel + "]";
	}

	

	

	
	

}
