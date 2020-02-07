package com.bridgelabz.demo.notesrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.demo.model.Notes;

public interface NotesRepository extends JpaRepository<Notes, Integer> {

}
