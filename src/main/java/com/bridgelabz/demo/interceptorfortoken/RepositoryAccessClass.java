package com.bridgelabz.demo.interceptorfortoken;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.userrepository.UserRepository;
@Service
//@Component
public class RepositoryAccessClass {
	
	 @Autowired
	 UserRepository userRepository;
	 
	public Optional<User> check (String emaiId){
		System.out.println("list"+userRepository.findAll());
		Optional<User> user = userRepository.findById(1);
		return user;
		
	}
	
}
