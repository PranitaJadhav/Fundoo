package com.bridgelabz.demo.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Label")

public class Label {
	@Id
	// @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "labelid")
	private int labelId;

	@Column(name = "createlabel_time")
	private LocalDateTime createlabel_time;

	@Column(name = "modified_time")
	private LocalDateTime modified_time;

	@NotEmpty(message = "field required")
	@Column(name = "label_name")
	private String label_name;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserInfo user;

	@ManyToMany
	
	/*
	 * @JoinTable( name = "Label_Notes", joinColumns = @JoinColumn(name =
	 * "labelid"), inverseJoinColumns = @JoinColumn(name = "nid"))
	 */
	 
	  private List<Notes> labelNotes;

	public Label() {
		super();
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

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	public List<Notes> getNotes() {
		return labelNotes;
	}

	public void setNotes(List<Notes> notes) {
		this.labelNotes = notes;
	}

	@Override
	public String toString() {
		return "Label [labelId=" + labelId + ", createlabel_time=" + createlabel_time + ", modified_time="
				+ modified_time + ", label_name=" + label_name + ", user=" + user + ", labelNotes=" + labelNotes + "]";
	}

	
	
	

}
