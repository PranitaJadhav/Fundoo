package com.bridgelabz.demo.notecontroller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.demo.dto.NotesDto;
import com.bridgelabz.demo.model.UserInfo;
import com.bridgelabz.demo.noteservice.NoteService;
import com.bridgelabz.demo.response.Response;
import com.bridgelabz.demo.userservice.UserService;
import com.bridgelabz.demo.utility.TokenService;

@RestController
@RequestMapping("/notes")
public class NoteController {
	@Autowired
	NoteService noteService;
	
	@Autowired
	UserService userservice;
	
	
	@Autowired
	UserInfo userInfo;
	
	@Autowired
	TokenService token;
	
	@PostMapping("/create")
	public Response add(@RequestBody NotesDto notesDto,@RequestBody String tok) throws UnsupportedEncodingException {
		String t	=	token.decodetoken(tok);
		//String t = token.decodetoken(token);
		return noteService.createNote(notesDto,t);
		
	}
	
}
