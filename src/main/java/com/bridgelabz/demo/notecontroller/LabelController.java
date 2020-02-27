package com.bridgelabz.demo.notecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.demo.dto.LabelDto;
import com.bridgelabz.demo.exception.Response;
import com.bridgelabz.demo.model.Label;
import com.bridgelabz.demo.noteservice.LabelService;
import com.bridgelabz.demo.utility.TokenService;
@RestController
@RequestMapping("/label")
public class LabelController {
	@Autowired
	LabelService labelService;
	@Autowired
	private TokenService tokenService;
	
	
	@PostMapping("/create")
	public String createLabel(@RequestBody LabelDto labelDto, @RequestParam String token) {
		return labelService.createLabel(labelDto, token);
		
	}
	@GetMapping("/getAllLabel")
	public List<Label> getAllLabels(@RequestHeader String token) {
		return labelService.getAllLabels(token);
		
	}
	@DeleteMapping("/deleteNote")
	public Response deleteLabel(@RequestHeader String token, @RequestParam int id) {
		labelService.deleteLabel(token,id);
		return new Response(200, "Deleted", null);
		
	}
	@PostMapping("/addLabelToNote")
	public Response addLabelToNote(@RequestHeader String token, @RequestParam int nid,@RequestParam int labelid) {
		labelService.addLabelToNote(token,nid,labelid);
		return new Response(200, "added", null);
		
		
	}
	@PostMapping("/addNoteToLabel")
	public Response addNoteToLabel(@RequestHeader String token, @RequestParam int nid,@RequestParam int labelid) {
		labelService.addNoteToLabel(token,nid,labelid);
		return new Response(200, "added", null);
		
		
	}
	@PostMapping("/updatelabel")
	public Response updateLabel(@RequestHeader String token, @RequestParam int labelId,@RequestBody LabelDto labelDto) {
		labelService.updateLabel(token,labelId,labelDto);
		return new Response(200, "added", null);
		
	}
}

