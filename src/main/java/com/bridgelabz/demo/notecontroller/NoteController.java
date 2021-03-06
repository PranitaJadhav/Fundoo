package com.bridgelabz.demo.notecontroller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.demo.dto.NotesDto;
import com.bridgelabz.demo.exception.Response;
import com.bridgelabz.demo.model.Collaborator;
import com.bridgelabz.demo.model.Notes;
import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.noteservice.NoteService;
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
	User userInfo;

	@Autowired
	private TokenService tokenService;
	
	
	


	@PostMapping("/create")
	public Response add(@RequestBody Notes notesDto, @RequestHeader String token)
			throws UnsupportedEncodingException {
		System.out.println(token);

		/*
		 * String t = tokenService.getUserToken(token); System.out.println("t  " + t);
		 */
		noteService.createNote(notesDto, token);
		System.out.println(token);
		return new Response(200, "Note created", null);

	}

	@DeleteMapping("/delete")
	public Response deleteById(@RequestBody int nid, @RequestParam String token) {
		String t = tokenService.getUserToken(token);

		noteService.deleteNoteByid(nid, t);

		return new Response(200, "Deleted", null);

	}

	@GetMapping("/getAllNotes")
	public List<Notes> getAll(@RequestParam String token)

	{
		String emailid = tokenService.getUserToken(token);

		return noteService.getAll(emailid);
	}

	@PutMapping("/updateNote")
	public Response updateNote(@RequestBody NotesDto noteDto, @RequestHeader int id, @RequestParam String token) {
		String t = tokenService.getUserToken(token);

		noteService.updateNote(noteDto, id, t);

		return new Response(200, "Updated", null);

	}

	@PutMapping("/trash")
	public Response trashNote(@RequestParam int nid, @RequestParam String token) {
		System.out.println(nid);
		String emailId = tokenService.getUserToken(token);

		noteService.trashNote(nid, emailId);

		return new Response(200, "Trashed", null);

	}

	@PutMapping("/unTrash")
	public Response unTrashNote(@RequestParam int nid, @RequestParam String token) {
		System.out.println(nid);
		String emailId = tokenService.getUserToken(token);

		noteService.unTrashNote(nid, emailId);

		return new Response(200, "UnTrashed", null);

	}

	@GetMapping("/getTrash")
	public List<Notes> getTrash(@RequestParam String token)

	{
		String emailid = tokenService.getUserToken(token);

		return noteService.getTrash(emailid);
	}

	@DeleteMapping("/deleteTrash")
	public Response deleteTrash(@RequestParam int nid, @RequestParam String token) {
		String emailid = tokenService.getUserToken(token);

		noteService.deleteTrash(nid, emailid);

		return new Response(200, "Deleted", null);

	}

	@PutMapping("/pin")
	public Response isPinUnpin(@RequestParam int nid, @RequestParam String token) {
		System.out.println(nid);
		String emailId = tokenService.getUserToken(token);

		noteService.isPinUnpin(nid, emailId);

		return new Response(200, "successfull", null);

	}

	@GetMapping("/getPin")
	public List<Notes> getPin(@RequestParam String token)

	{
		String emailid = tokenService.getUserToken(token);

		return noteService.getPin(emailid);
	}

	@PutMapping("/archive")
	public Response isArchive(@RequestParam int nid, @RequestParam String token) {
		System.out.println(nid);
		String emailId = tokenService.getUserToken(token);

		noteService.isArchive(nid, emailId);

		return new Response(200, "successfull", null);

	}

	@PutMapping("/Unarchive")
	public Response isUnArchive(@RequestParam int nid, @RequestParam String token) {
		System.out.println(nid);
		String emailId = tokenService.getUserToken(token);

		noteService.isUnArchive(nid, emailId);

		return new Response(200, "successfull", null);

	}

	@GetMapping("/getArchivesNotes")
	public List<Notes> getArchivesNotes(@RequestParam String token)

	{
		String emailid = tokenService.getUserToken(token);

		return noteService.getArchivesNotes(emailid);
	}

	
	  @PostMapping("/collaborate") 
	  public String collaborate(@RequestHeader int noteId, @RequestHeader String userEmailId,@RequestHeader String token) { String owneremailid =
	  tokenService.getUserToken(token); 
	  return noteService.collaborateUSer(noteId,userEmailId, owneremailid);
	  
	  }
	  @DeleteMapping("/deleteCollaborate")
	  public String deleteCollaborate(@RequestHeader int noteId, @RequestHeader String userEmailId, @RequestHeader String token) { 
		  String owneremailid = tokenService.getUserToken(token); 
			  return noteService.deleteCollaborate(noteId,userEmailId, owneremailid);
			  
			  }
	  @GetMapping("/getcollaborator")
		public List<Collaborator> getAllCollaborator(@RequestHeader String token, @RequestHeader int noteID)

		{
			String emailid = tokenService.getUserToken(token);

			return noteService.getAllCollaborator(emailid,noteID);
		}

	 
	@GetMapping("/searchByTitle")
	public List<Notes> searchByTitle(@RequestHeader String token,@RequestHeader String title)

	{
		String emailid = tokenService.getUserToken(token);

		return noteService.searchByTitle(emailid,title);
	}
	@PutMapping("/reminder")
	public Response reminder(@RequestHeader int nid, @RequestHeader String token,@RequestHeader String time) {
		System.out.println(nid);
		String emailId = tokenService.getUserToken(token);

		return noteService.reminder(nid, emailId,time);


	}
	@PutMapping("/removeReminder")
	public Response removeReminder(@RequestHeader int nid, @RequestHeader String token) {
		System.out.println(nid);
		String emailId = tokenService.getUserToken(token);

		return	noteService.removeReminder(nid, emailId);


	}


}
