package com.bridgelabz.demo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class LabelDto {
	//private int labelId;
	@NotEmpty(message = "field required")
    @Pattern(regexp = "^[a-z0-9_-]{2,20}$",message = "Enter valid label")
	private String labelName;
	


	public LabelDto() {
		super();
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
