package com.bridgelabz.demo.interceptorfortoken;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bridgelabz.demo.noteservice.NoteService;

@Component
public class Interceptor extends HandlerInterceptorAdapter{
	Logger log	=	(Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	NoteService service;
	
	//Logger log	=	LoggerFactory.getLogger(NoteService.getClass());


	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("prehandler");
		
		String token = request.getHeader("token");
		String email = service.tokenReturn(token);
		System.out.println(email);
		//response.setHeader(token, email);
		request.setAttribute(token, email);
		log.info("request is completed");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("posthandler");
		log.info("request is completed");

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("after completion");
		log.info("request is completed");

	}

	

}
