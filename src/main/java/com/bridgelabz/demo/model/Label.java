package com.bridgelabz.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity

public class Label   {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int labelId;

	private LocalDateTime createlabel_time;

	private LocalDateTime modified_time;

	@NotEmpty(message = "field required")
	//@Column(name = "label_name")
	private String label_name;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToMany

	@JoinTable(name = "Label_Notes_join", joinColumns = @JoinColumn(name = "labelid"), inverseJoinColumns = @JoinColumn(name = "nid"))
	@JsonIgnoreProperties(value = "listLabel")
	private List<Notes> listofNotes = new ArrayList<Notes>();
	// private List<Notes> labelNotes;

	public Label() {
		super();
	}

	public Label(int labelId, LocalDateTime createlabel_time, LocalDateTime modified_time,
			@NotEmpty(message = "field required") String label_name, User user, List<Notes> listofNotes) {
		super();
		this.labelId = labelId;
		this.createlabel_time = createlabel_time;
		this.modified_time = modified_time;
		this.label_name = label_name;
		this.user = user;
		this.listofNotes = listofNotes;
	}

	public int getLabelId() {
		return labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	public LocalDateTime getCreatelableTime() {
		return createlabel_time;
	}

	public void setCreatelableTime(LocalDateTime createlableTime) {
		this.createlabel_time = createlableTime;
	}

	public LocalDateTime getModifiedTime() {
		return modified_time;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modified_time = modifiedTime;
	}

	public String getLabelName() {
		return label_name;
	}

	public void setLabelName(String labelName) {
		this.label_name = labelName;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Notes> getListofNotes() {
		return listofNotes;
	}

	public void setListofNotes(List<Notes> listofNotes) {
		this.listofNotes = listofNotes;
	}

	

}
