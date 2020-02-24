package com.bridgelabz.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	
	@Column(name = "description")
	private String description;
	
	@Column(name = "createlabel_time")
	private LocalDateTime createlabel_time;

	@Column(name = "modified_time")
	private LocalDateTime modified_time;
	
	@NotNull
	@Column(name = "trash")
	private boolean isTrash;
	
	@NotNull
	@Column(name = "pin")
	private boolean isPin;
	
		@NotNull
	  @Column(name = "archive") 
	  private boolean isArchive;
	 
	

	@ManyToOne
	@JoinColumn(name = "user_id")
	
	private UserInfo user;

	@ManyToMany
	@JoinTable ( name = "Label_Notes_join", joinColumns = @JoinColumn (name
			  ="nid"), inverseJoinColumns = @JoinColumn(name = "labelid"))
	
	@JsonIgnoreProperties(value = "listofNotes")
	private List<Label> listLabel = new ArrayList<Label>();
	
	
	  @ManyToMany
	  @JoinTable ( name = "Collaborator_Notes_join", joinColumns = @JoinColumn (name
			  ="nid"), inverseJoinColumns = @JoinColumn(name = "collaboratoremail"))
	 @JsonIgnoreProperties(value = "noteList")
	  private List<Collaborator> collaboratorEmailId = new ArrayList<Collaborator>();
	 

	//private List<Label> notesLabel;
	
	/*
	 * @ManyToMany
	 * 
	 * @JoinTable (name = "Notes_UserInfo_join", joinColumns = @JoinColumn (name
	 * ="nid"), inverseJoinColumns = @JoinColumn(name = "id"))
	 * 
	 * @JsonIgnoreProperties(value = "collaborateList") private List<UserInfo>
	 * collaborateUser;
	 */
	
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

	public LocalDateTime getCreatelabel_time() {
		return createlabel_time;
	}

	public void setCreatelabel_time(LocalDateTime createlabel_time) {
		this.createlabel_time = createlabel_time;
	}

	public LocalDateTime getModified_time() {
		return modified_time;
	}

	public void setModified_time(LocalDateTime modified_time) {
		this.modified_time = modified_time;
	}

	public boolean isTrash() {
		return isTrash;
	}

	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}

	public boolean isPin() {
		return isPin;
	}

	public void setPin(boolean isPin) {
		this.isPin = isPin;
	}

	
	  public boolean isArchive() { return isArchive; }
	  
	  public void setArchive(boolean isArchive) { this.isArchive = isArchive; }
	 

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




	public List<Collaborator> getCollaboratorEmail() {
		return collaboratorEmailId;
	}




	public void setCollaboratorEmail(List<Collaborator> collaboratorEmail) {
		this.collaboratorEmailId = collaboratorEmail;
	}




	@Override
	public String toString() {
		return "Notes [nid=" + nid + ", title=" + title + ", description=" + description + ", createlabel_time="
				+ createlabel_time + ", modified_time=" + modified_time + ", isTrash=" + isTrash + ", isPin=" + isPin
				+ ", isArchive=" + isArchive + ", user=" + user + ", listLabel=" + listLabel + ", collaboratorEmailId="
				+ collaboratorEmailId + "]";
	}




	






	






	
	
}
