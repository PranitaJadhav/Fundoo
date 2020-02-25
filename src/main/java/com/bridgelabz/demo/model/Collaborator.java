package com.bridgelabz.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Collaborator {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	
	private String collaboratoremail;

	@ManyToMany
	@JoinTable(name = "Collaborator_Notes_join", joinColumns = @JoinColumn(name = "collaboratoremail"), inverseJoinColumns = @JoinColumn(name = "nid"))
	@JsonIgnoreProperties(value = "collaboratorEmailId")
	private List<Notes> noteList = new ArrayList<Notes>();

	public Collaborator() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollaboratoremail() {
		return collaboratoremail;
	}

	public void setCollaboratoremail(String collaboratoremail) {
		this.collaboratoremail = collaboratoremail;
	}

	public List<Notes> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<Notes> noteList) {
		this.noteList = noteList;
	}


	
}
