package com.bridgelabz.demo.noteservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.demo.dto.LabelDto;
import com.bridgelabz.demo.exception.ValueFoundNull;
import com.bridgelabz.demo.labelrepository.LabelRepository;
import com.bridgelabz.demo.model.Label;
import com.bridgelabz.demo.model.Notes;
import com.bridgelabz.demo.model.UserInfo;
import com.bridgelabz.demo.notesrepository.NotesRepository;
import com.bridgelabz.demo.userrepository.UserRepository;
import com.bridgelabz.demo.utility.TokenService;

@Service
public class LabelService implements LabelInterface {
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	TokenService tokenService;
	@Autowired
	Notes notes;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LabelRepository labelRepository;

	@Autowired
	UserInfo userInfo;
	@Autowired
	NotesRepository noteRepository;

	@Override
	public String createLabel( LabelDto labelDto, String token) {
		String t	=	tokenService.getUserToken(token);
		System.out.println("t  "+t);
		Label label	=	modelMapper.map(labelDto, Label.class);
		Optional<UserInfo> user = userRepository.findByEmailid(t);
		if(!user.isPresent()) {
			throw new ValueFoundNull("user not found");
			
		}
		
		label.setCreatelableTime(LocalDateTime.now());
		label.setModifiedTime(LocalDateTime.now());
		label.setUser(user.get());
		labelRepository.save(label);
		return "added";
				
	
	}

	@Override
	public List<Label> getAllLabels(String token) {
		String t	=	tokenService.getUserToken(token);
		System.out.println(t);
		Optional<UserInfo> userExist	=	userRepository.findByEmailid(t);
		if(!userExist.isPresent())
			throw new ValueFoundNull("User Doesnt exist");
		else
		return (List<Label>) labelRepository.findAll();
		
	
	}

	public void addLabelToNote(String token, int noteId, int labelId) {
		String UserEmailid	=	tokenService.getUserToken(token);
		System.out.println(UserEmailid);
		Optional<UserInfo> userExist	=	userRepository.findByEmailid(UserEmailid);
		int id	=	userExist.get().getId();
		System.out.println("id"+id);
		Optional<Notes> noteExist	=	noteRepository.findById(noteId);
		Optional<Label> labelExist	=	labelRepository.findByUserId(labelId);
		System.out.println(noteExist);
		System.out.println("hey");
		System.out.println(labelExist);

		/*
		if (!userExist.isPresent()) {
			throw new RuntimeException("User doesnt exist");
		}
		else if (!noteExist.isPresent()) {
			throw new RuntimeException("Notes doesnt exist");

		}
		 if (!labelExist.isPresent()) {
			throw new RuntimeException("label doesnt exist");

			
		}*/
		//else {
			System.out.println(labelExist);
			noteExist.get().getLabel().add(labelExist.get());
			
			//labelExist.get().getNotes().add(labelExist.get());
			//noteExist.get().setLabel(labelExist.get().setLabelId(labelId));
			
		
			noteRepository.save(noteExist.get());
		//}
	}
		
		
}
	


