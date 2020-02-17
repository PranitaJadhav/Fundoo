package com.bridgelabz.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@ManyToMany(mappedBy = "labelNotes")
	private List<Label> notesLabel;

	public Notes() {
		super();
	}

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

	public List<Label> getLabel() {
		return notesLabel;
	}

	public void setLabel(List<Label> label) {
		this.notesLabel = label;
	}

	@Override
	public String toString() {
		return "Notes [nid=" + nid + ", title=" + title + ", description=" + description + ", user=" + user
				+ ", like_notes=" + notesLabel + "]";
	}
	

}
