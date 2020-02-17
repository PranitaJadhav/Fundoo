package com.bridgelabz.demo.noteservice;

import java.util.List;

import com.bridgelabz.demo.dto.LabelDto;
import com.bridgelabz.demo.model.Label;
import com.sun.mail.iap.Response;

public interface LabelInterface {
	public String createLabel( LabelDto labelDto, String token);
	public List<Label> getAllLabels(String token);
}
