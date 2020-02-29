
package com.bridgelabz.demo.interceptorfortoken;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.noteservice.LabelService;
import com.bridgelabz.demo.userrepository.UserRepository;
import com.bridgelabz.demo.utility.TokenService;

@Service
public class Interceptor extends HandlerInterceptorAdapter {

//	@Autowired
//	TokenService tokenService;

	
	 @Autowired 
	 UserRepository userRepository;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		System.out.println("prehandler");

		TokenService tokenService=new TokenService();
		
		String token1 = request.getHeader("token");
		System.out.println(token1);
		String email1 = tokenService.getUserToken(token1);
		System.out.println(email1);
		LabelService repo1 = new LabelService(); 
		repo1.check(email1);
		System.out.println("check"+repo1.check(email1).isPresent());
		// List<User> user = repo.findAll(); 
		 // System.out.println(user.get(0));
		
		Optional<User> user = userRepository.findByEmailid("pranitajadhav1959@gmail.com");
		System.out.println("hey");
		System.out.println(user.isPresent());

		//System.out.println(user1.get());
		if (user.isPresent()) {
			return true;
		}

		return false;

	}
	/*
	 * @Override public void postHandle(HttpServletRequest request,
	 * HttpServletResponse response, Object handler, ModelAndView modelAndView)
	 * throws Exception { System.out.println("posthandler"); //
	 * log.info("request is completed");
	 * 
	 * }
	 * 
	 * @Override public void afterCompletion(HttpServletRequest request,
	 * HttpServletResponse response, Object handler, Exception ex) throws Exception
	 * { System.out.println("after completion"); //
	 * log.info("request is completed");
	 * 
	 * }
	 */

}
