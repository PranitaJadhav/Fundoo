package com.bridgelabz.demo.noteservice;


import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bridgelabz.demo.dto.NotesDto;
import com.bridgelabz.demo.exception.ValueFoundNull;
import com.bridgelabz.demo.model.Notes;
import com.bridgelabz.demo.model.UserInfo;
import com.bridgelabz.demo.notesrepository.NotesRepository;
import com.bridgelabz.demo.response.Response;
import com.bridgelabz.demo.userrepository.UserRepository;
import com.bridgelabz.demo.utility.TokenService;

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

	@Autowired
	TokenService tokenService;
	@Autowired
	Notes notes;
	@Autowired
	Environment environment;

	public Response createNote(NotesDto notesDto, String token){

		Notes notes = modelMapper.map(notesDto, Notes.class);
		
		 
		  
		  
		try {

			if (!(notesDto.getTitle().isEmpty() && notesDto.getDescription().isEmpty())) {

				Optional<UserInfo> user = userRepository.findByEmailid(token);

				notes.setUser(user.get());
				notesRepository.save(notes);
				return new Response(200, "note created", null);
			}

		} catch (Exception e) {
			return null;
		}
		return new Response(500, "null value", null);
	}
		 
	

	public void deleteNoteByid(int id, String token) {
		System.out.println(token);
		Optional<UserInfo> user = userRepository.findByEmailid(token);
		System.out.println(id);
	
		Optional<Notes> note	=	notesRepository.findByNid(id);

		
		if(user.isPresent()) {
		
		  notesRepository.deleteById(id);
		}
		 

	}
	public List<Notes> getAll(String token) {
		Optional<UserInfo> userExist = userRepository.findByEmailid(token);
		if(!userExist.isPresent())
			throw new ValueFoundNull("User Doesnt exist");
		else
		return notesRepository.findAll();
		

	}
	
	public void updateNote(NotesDto noteDto, int id, String token) {
		System.out.println(token);
		Optional<Notes> note	=	notesRepository.findByNid(id);
		System.out.println(note);
		Optional<UserInfo> user = userRepository.findByEmailid(token);
		if(user.isPresent()) {
			if(note.isPresent()) {
			note.get().setTitle(noteDto.getTitle());
			note.get().setDescription(noteDto.getDescription());
			notesRepository.save(note.get());

			}
		}

		
	}

}
