package com.bridgelabz.demo.labelrepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bridgelabz.demo.model.Label;
import com.bridgelabz.demo.model.Note_Label;
import com.bridgelabz.demo.model.Notes;


public interface LabelRepository extends CrudRepository<Label, Integer>{
	Optional<Label> findByUserId(int id);


	 
}
