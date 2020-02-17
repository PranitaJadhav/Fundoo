package com.bridgelabz.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
@Embeddable
@Table(name = "label_label_notes")

public class Label_Label_Notes implements Serializable{
	@Id
	int id;
	
	@Column(name = "notes_label_labelid")
	int notes_label_labelid;
	
	@Column(name = "label_notes_nid")
	int label_notes_nid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNotes_label_labelid() {
		return notes_label_labelid;
	}

	public void setNotes_label_labelid(int notes_label_labelid) {
		this.notes_label_labelid = notes_label_labelid;
	}

	public int getLabel_notes_nid() {
		return label_notes_nid;
	}

	public void setLabel_notes_nid(int label_notes_nid) {
		this.label_notes_nid = label_notes_nid;
	}

	@Override
	public String toString() {
		return "Label_Label_Notes [id=" + id + ", notes_label_labelid=" + notes_label_labelid + ", label_notes_nid="
				+ label_notes_nid + "]";
	}
	
	

}
