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
		Optional<Notes> noteExist	=	noteRepository.findByNid(noteId);
		Optional<Label> labelExist	=	labelRepository.findByLabelId(labelId);
		System.out.println(noteExist);
		System.out.println("hey");
		System.out.println(labelExist);
		if(!userExist.isPresent()) {
			
			throw new RuntimeException("User doesnt exist");
		}
		else if (!noteExist.isPresent()) {
			throw new RuntimeException("Note is not present");
			
		}
		else if (!labelExist.isPresent()) {
			throw new RuntimeException("label is not present");

			
		}
		else {
		
		noteExist.get().getListLabel().add(labelExist.get());
		
		noteRepository.save(noteExist.get());
		
		}
		
		
		/*
		 * List<Label> labelList = labelRepository.findByUserId(id); Notes note= (Notes)
		 * labelList.get(0).getListofNotes().stream().filter(e -> e.getNid()== noteId);
		 * 
		 * 
		 * noteExist.get().setListLabel(labelList); System.out.println("hey10");
		 * labelRepository.save(labelExist.get()); System.out.println("hey11");
		 */
	}
	
	public void addNoteToLabel(String token, int noteId, int labelId) {
		String UserEmailid	=	tokenService.getUserToken(token);
		System.out.println(UserEmailid);
		Optional<UserInfo> userExist	=	userRepository.findByEmailid(UserEmailid);
		int id	=	userExist.get().getId();
		System.out.println("id"+id);
		System.out.println(noteId);
		System.out.println("note"+noteRepository.findByNid(noteId));
		Optional<Notes> noteExist	=	noteRepository.findByNid(noteId);
	
		System.out.println("hey2");
		Optional<Label> labelExist	=	labelRepository.findByLabelId(labelId);
		System.out.println("hey3");
		System.out.println(noteExist.get());
		System.out.println("hey");
		System.out.println(labelExist.get());
		
		if(!userExist.isPresent()) {
			
			throw new RuntimeException("User doesnt exist");
		}
		else if (!noteExist.isPresent()) {
			throw new RuntimeException("Note is not present");
			
		}
		else if (!labelExist.isPresent()) {
			throw new RuntimeException("label is not present");

			
		}
		else {
			labelExist.get().getListofNotes().add(noteExist.get());
			noteRepository.save(noteExist.get());
														
			
		}
																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																												
		
		

		/*
		 * List<Notes> listnote = noteRepository.findByUserId(id);
		 * 
		 * System.out.println(listnote); labelExist.get().setListofNotes(listnote);
		 * System.out.println("hey5"); noteRepository.save(noteExist.get());
		 */
		}

	public void updateLabel(String token, int labelId, LabelDto labelDto) {
		String UserEmailid	=	tokenService.getUserToken(token);
		System.out.println(UserEmailid);
		Optional<UserInfo> userExist	=	userRepository.findByEmailid(UserEmailid);
		System.out.println(userExist);
		Optional<Label> labelExist	=	labelRepository.findByLabelId(labelId);
		System.out.println(labelExist);

			if(!userExist.isPresent()) {
				throw new ValueFoundNull("user not found");
				
			}
			else if (!labelExist.isPresent()) {
				throw new ValueFoundNull("label not found");

				
			}
			else {
				
				labelExist.get().setLabelName(labelDto.getLabelName());
				System.out.println("hey");
				labelExist.get().setModifiedTime(LocalDateTime.now());
				labelRepository.save(labelExist.get());
			}
		}

	public void deleteLabel(String token, int id) {
		String UserEmailid	=	tokenService.getUserToken(token);
		System.out.println(UserEmailid);
		Optional<UserInfo> userExist	=	userRepository.findByEmailid(UserEmailid);
		System.out.println(userExist);
		System.out.println(id);
		Optional<Label> labelExist	=	labelRepository.findByLabelId(id);

		System.out.println("label"+labelExist);

			if(!userExist.isPresent()) {
				throw new ValueFoundNull("user not found");
				
			}
			else if (!labelExist.isPresent()) {
				throw new ValueFoundNull("label not found");

				
			}
			else {
				labelRepository.delete(labelExist.get());
		
			}
		}
		
	}
	
	

	


