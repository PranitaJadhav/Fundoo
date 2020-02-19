package com.bridgelabz.demo.notesrepository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.demo.model.Notes;
import com.bridgelabz.demo.model.UserInfo;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {
	Optional<Notes> findByNid(int id);
	List<Notes> findByUserId(int id);


	
	
	

	


}
