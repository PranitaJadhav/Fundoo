package com.bridgelabz.demo.interceptorfortoken;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.noteservice.NoteService;
import com.bridgelabz.demo.userrepository.UserRepository;
import com.bridgelabz.demo.utility.TokenService;

@Component
public class Interceptor extends HandlerInterceptorAdapter {
	
	  @Autowired NoteService service;
	  
	 // @Autowired TokenService tokenService;
	  
	  @Autowired UserRepository repo;
	 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		System.out.println("prehandler");

		String token = request.getHeader("token");
		System.out.println(token);
		String email1	=	NoteService.tokenReturn(token);
		System.out.println(email1);

		Optional<User> user =  repo.findByEmailid(email1);
		if(user.isPresent()) {
			return true;
		}
		return false;
		  
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("posthandler");
		// log.info("request is completed");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("after completion");
		// log.info("request is completed");

	}

}
