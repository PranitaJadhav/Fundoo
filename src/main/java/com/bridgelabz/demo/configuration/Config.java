package com.bridgelabz.demo.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bridgelabz.demo.model.Notes;
import com.bridgelabz.demo.model.UserInfo;

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
	public UserInfo getUserInfo() {
		return new UserInfo();

	}
	@Bean
	public Notes getNotes() {
		return new Notes();

	}
	
	 
	 
	 
}
