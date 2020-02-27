package com.bridgelabz.demo.model;

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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Notes {
	@Id
	@Column(name = "nid")
	@GeneratedValue(strategy = GenerationType.AUTO)

	private int nid;

	@NotBlank(message = "title is mandatory")
    @Pattern(regexp = "^[a-z0-9_-]{2,20}$",message = "Enter valid title")
	private String title;

	private String description;

	private LocalDateTime createlabel_time;

	private LocalDateTime modified_time;

	@NotNull
	private boolean isTrash;

	@NotNull
	private boolean isPin;

	@NotNull
	private boolean isArchive;

	private boolean isReminder;

	private LocalDateTime reminder;

	@ManyToOne
	@JoinColumn(name = "user_id")

	private User user;

	@ManyToMany
	@JoinTable(name = "Label_Notes_join", joinColumns = @JoinColumn(name = "nid"), inverseJoinColumns = @JoinColumn(name = "labelid"))
	@JsonIgnoreProperties(value = "listofNotes")
	private List<Label> listLabel = new ArrayList<Label>();

	@ManyToMany
	@JoinTable(name = "Collaborator_Notes_join", joinColumns = @JoinColumn(name = "nid"), inverseJoinColumns = @JoinColumn(name = "collaboratoremail"))
	@JsonIgnoreProperties(value = "noteList")
	private List<Collaborator> collaboratorEmailId = new ArrayList<Collaborator>();

	

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

	public boolean isArchive() {
		return isArchive;
	}

	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
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

	public boolean isReminder() {
		return isReminder;
	}

	public void setReminder(boolean isReminder) {
		this.isReminder = isReminder;
	}

	public LocalDateTime getReminder() {
		return reminder;
	}

	public void setReminder(LocalDateTime reminder) {
		this.reminder = reminder;
	}

	

}
