package com.bridgelabz.demo.noteservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public Response createNote(NotesDto notesDto, String token) {

		Notes notes = modelMapper.map(notesDto, Notes.class);

		try {

			if (!(notesDto.getTitle().isEmpty() && notesDto.getDescription().isEmpty())) {

				Optional<UserInfo> user = userRepository.findByEmailid(token);

				notes.setUser(user.get());
				notes.setCreatelabel_time(LocalDateTime.now());
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

		Optional<Notes> note = notesRepository.findByNid(id);

		if (user.isPresent()) {

			notesRepository.deleteById(id);
		}

	}

	public List<Notes> getAll(String token) {
		System.out.println(token);
		Optional<UserInfo> userExist = userRepository.findByEmailid(token);
		int id = userExist.get().getId();
		System.out.println(id);
		List<Notes> noteList = notesRepository.findAll();

		List<Notes> noteList2 = noteList.stream().filter(i -> (i.getUser().getEmailid()).equals((token)))
				.collect(Collectors.toList());

		if (!userExist.isPresent())
			throw new ValueFoundNull("User Doesnt exist");
		else
			return noteList2;

	}

	public void updateNote(NotesDto noteDto, int id, String token) {
		System.out.println(token);
		Optional<Notes> note = notesRepository.findByNid(id);
		System.out.println(note.get());
		Optional<UserInfo> user = userRepository.findByEmailid(token);

		if (user.isPresent()) {
			if (note.isPresent()) {

				note.get().setTitle(noteDto.getTitle());
				note.get().setDescription(noteDto.getDescription());
				note.get().setModified_time(LocalDateTime.now());
				notesRepository.save(note.get());

			}
		}

	}

	public void trashNote(int id, String emailId) {
		System.out.println(emailId);
		Optional<Notes> note = notesRepository.findByNid(id);
		Optional<UserInfo> user = userRepository.findByEmailid(emailId);

		if (!user.isPresent()) {
			throw new ValueFoundNull(environment.getProperty("null value found"));

		} else if (!note.isPresent()) {
			throw new ValueFoundNull(environment.getProperty("null value found"));

		}

		else {
			if (note.get().isPin()) {
				note.get().setPin(false);

			}

			note.get().setTrash(true);
			note.get().setModified_time(LocalDateTime.now());
			notesRepository.save(note.get());

		}

	}

	public void unTrashNote(int id, String emailId) {
		System.out.println(emailId);
		Optional<Notes> note = notesRepository.findByNid(id);
		Optional<UserInfo> user = userRepository.findByEmailid(emailId);

		if (!user.isPresent()) {
			throw new ValueFoundNull(environment.getProperty("null value found"));

		} else if (!note.isPresent()) {
			throw new ValueFoundNull(environment.getProperty("null value found"));

		} else if (note.get().isTrash()) {

			note.get().setTrash(false);
			note.get().setModified_time(LocalDateTime.now());
			notesRepository.save(note.get());
		}
	}
	public List<Notes> getTrash(String emailid) {
		Optional<UserInfo> user = userRepository.findByEmailid(emailid);
		int id = user.get().getId();
		List<Notes> notes = notesRepository.findByUserId(id);
		List<Notes> notesList = notes.stream().filter(i -> i.isTrash()).collect(Collectors.toList());
		/*
		 * for(Notes userNotes:notes) { if(userNotes.isArchive()) {
		 * notesList.add(userNotes); } }
		 */

		return notesList;
	}

	public void deleteTrash(int nid, String emailid) {
		Optional<Notes> note = notesRepository.findByNid(nid);
		System.out.println(note);
		Optional<UserInfo> user = userRepository.findByEmailid(emailid);

		if (!user.isPresent()) {
			throw new ValueFoundNull(environment.getProperty("null value found"));

		} else if (!note.isPresent()) {
			throw new ValueFoundNull(environment.getProperty("null value found"));

		} else if (!note.get().isTrash()) {
			throw new ValueFoundNull(environment.getProperty("Not present in trash"));

		} else {
			notesRepository.delete(note.get());
			notesRepository.save(note.get());

		}

	}

	public void isPinUnpin(int nid, String emailId) {
		Optional<Notes> note = notesRepository.findByNid(nid);
		Optional<UserInfo> user = userRepository.findByEmailid(emailId);

		if (!user.isPresent()) {
			throw new ValueFoundNull(environment.getProperty("null value found"));

		} else if (!note.isPresent()) {
			throw new ValueFoundNull(environment.getProperty("null value found"));

		} else {

			if (note.get().isPin()) {
				note.get().setPin(false);
				notesRepository.save(note.get());
			} else {

				note.get().setPin(true);
				notesRepository.save(note.get());
			}
		}

	}
	public List<Notes> getPin(String emailid) {
		Optional<UserInfo> user = userRepository.findByEmailid(emailid);
		int id = user.get().getId();
		List<Notes> notes = notesRepository.findByUserId(id);
		List<Notes> notesList = notes.stream().filter(i -> i.isPin()).collect(Collectors.toList());
		/*
		 * for(Notes userNotes:notes) { if(userNotes.isArchive()) {
		 * notesList.add(userNotes); } }
		 */

		return notesList;
	}
	
	

	public String isArchive(int nid, String emailId) {

		Optional<Notes> note = notesRepository.findByNid(nid);
		Optional<UserInfo> user = userRepository.findByEmailid(emailId);

		if (!user.isPresent()) {
			throw new ValueFoundNull(environment.getProperty("null value found"));

		}
		if (!note.isPresent()) {
			throw new ValueFoundNull(environment.getProperty("null value found"));

		}
		if (note.get().isPin()) {

			note.get().setPin(false);

			note.get().setArchive(true);
			notesRepository.save(note.get());
		}

		else {

			note.get().setArchive(true);
			notesRepository.save(note.get());

		}

		return "done";
	}
	public String isUnArchive(int nid, String emailId) {

		Optional<Notes> note = notesRepository.findByNid(nid);
		Optional<UserInfo> user = userRepository.findByEmailid(emailId);

		if (!user.isPresent()) {
			throw new ValueFoundNull(environment.getProperty("null value found"));

		}
		if (!note.isPresent()) {
			throw new ValueFoundNull(environment.getProperty("null value found"));

		}
		if (note.get().isArchive()) {


			note.get().setArchive(false);
			notesRepository.save(note.get());
		}

	
		return "done";
	}


	public List<Notes> getArchivesNotes(String emailid) {
		Optional<UserInfo> user = userRepository.findByEmailid(emailid);
		int id = user.get().getId();
		List<Notes> notes = notesRepository.findByUserId(id);
		List<Notes> notesList = notes.stream().filter(i -> i.isArchive()).collect(Collectors.toList());
		/*
		 * for(Notes userNotes:notes) { if(userNotes.isArchive()) {
		 * notesList.add(userNotes); } }
		 */

		return notesList;
	}

	

}
