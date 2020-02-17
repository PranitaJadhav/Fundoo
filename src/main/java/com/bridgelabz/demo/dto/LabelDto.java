package com.bridgelabz.demo.dto;

import javax.validation.constraints.NotEmpty;

public class LabelDto {
	//private int labelId;
	@NotEmpty(message = "field required")
	private String labelName;
	


	public LabelDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LabelDto(@NotEmpty(message = "field required") String labelName) {
		super();
		this.labelName = labelName;
	}
	/*
	 * public int getLabelId() { return labelId; } public void setLabelId(int
	 * labelId) { this.labelId = labelId; }
	 */
	public String getLabelName() {
		return labelName;
	}
	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	@Override
	public String toString() {
		return "LabelDto [labelName=" + labelName + "]";
	}
	
	
	
	

}
