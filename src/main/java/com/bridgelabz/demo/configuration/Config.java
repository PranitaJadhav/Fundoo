package com.bridgelabz.demo.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bridgelabz.demo.interceptorfortoken.RepositoryAccessClass;
import com.bridgelabz.demo.model.Notes;
import com.bridgelabz.demo.model.User;

@Configuration
public class Config {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	
	
	  @Bean
	  public BCryptPasswordEncoder name() { 
		  return new BCryptPasswordEncoder(); }
	 
	  
	@Bean
	public User getUserInfo() {
		return new User();

	}
	@Bean
	public Notes getNotes() {
		return new Notes();

	}
	
	
	/*
	 * public Interceptor getInt() { return new Interceptor();
	 * 
	 * }
	 */
	 
	 
}
