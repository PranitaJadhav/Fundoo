package com.bridgelabz.demo.notesrepository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.demo.model.Notes;
import com.bridgelabz.demo.model.UserInfo;

public interface NotesRepository extends JpaRepository<Notes, Integer> {
	Optional<Notes> findByNid(int id);


	
	
	

	


}
