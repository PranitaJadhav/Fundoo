package com.bridgelabz.demo.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bridgelabz.demo.model.UserInfo;

@Configuration
public class Config {
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	/*
	 * @Bean public BCryptPasswordEncoder name() { BCryptPasswordEncoder
	 * passwordEncoder = new BCryptPasswordEncoder(); return passwordEncoder; }
	 */
	  
	@Bean
	public UserInfo getUserInfo() {
		return new UserInfo();

	}
	 
}
