package com.bridgelabz.demo.labelrepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.demo.model.Label;


public interface LabelRepository extends JpaRepository<Label, Integer>{
	//Optional<Label> findByUserId(int id);
	Optional<Label> findByLabelId(int id);
	List<Label> findByUserId(int id);
	
	//List<Label> findByLabelId(int id);


	 
}
