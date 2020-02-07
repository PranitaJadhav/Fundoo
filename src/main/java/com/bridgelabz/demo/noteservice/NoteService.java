package com.bridgelabz.demo.noteservice;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.demo.dto.NotesDto;
import com.bridgelabz.demo.model.Notes;
import com.bridgelabz.demo.model.UserInfo;
import com.bridgelabz.demo.notesrepository.NotesRepository;
import com.bridgelabz.demo.response.Response;
import com.bridgelabz.demo.userrepository.UserRepository;

@Service
public class NoteService {
	
	@Autowired
	NotesRepository notesRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserInfo userInfo;
	
	@Autowired
	ModelMapper modelMapper;
	

	
	public Response createNote(NotesDto notesDto,String token) {

		
	Notes notes	=	modelMapper.map(notesDto, Notes.class);
	UserInfo user	=	userRepository.findByEmailid(token);
	notes.setUser(user);
	notesRepository.save(notes);
	
	return null;
	
		
	}
	

}
