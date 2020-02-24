package com.bridgelabz.demo.labelrepository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.demo.model.Collaborator;
@Repository
public interface CollaboratorRepository extends JpaRepository<Collaborator, Long>{
	//Optional<Collaborator> findByCollaboratorEmail(String email);
	//Optional<Collaborator> findByCollaboratorNotes(int noteId);
	Optional<Collaborator> findBycollaboratoremail(String email);
	//List<Collaborator> findby


}
