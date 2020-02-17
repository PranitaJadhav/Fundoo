package com.bridgelabz.demo.userservice;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.demo.dto.ForgetPasswordDto;
import com.bridgelabz.demo.dto.LoginDto;
import com.bridgelabz.demo.dto.ResetPasswordDto;
import com.bridgelabz.demo.dto.UserDto;
import com.bridgelabz.demo.model.UserInfo;
import com.bridgelabz.demo.response.Response;
import com.bridgelabz.demo.userrepository.UserRepository;
import com.bridgelabz.demo.utility.JMS;
import com.bridgelabz.demo.utility.TokenService;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	TokenService tokenService;

	@Autowired
	JMS jms;

	List<UserInfo> user = new ArrayList<UserInfo>();

	public Response addUser(UserDto userdto) throws UnsupportedEncodingException {
		System.out.println("hey3");
		
											//source	destination
		UserInfo userInfo = modelMapper.map(userdto, UserInfo.class);

		Optional<UserInfo> userExist = userRepository.findByEmailid(userdto.getEmailid());
		if (userExist.isPresent() ) {
			return new Response(200, "User Exist", null);

		}

		else if (userdto.getPassword().equals(userdto.getConfirmPassword())) {

			String message	=	"Registered Successfully";
			System.out.println(message);
			jms.sendMail(userdto.getEmailid(), message,null);
			
			
			userRepository.save(userInfo);
			

		}
		

		return new Response(500, "Unkonwn Error", null);

	}

	public List<UserInfo> getAll(String token) {
		String tok	=	tokenService.getUserToken(token);
		Optional<UserInfo> userExist = userRepository.findByEmailid(tok);

		if (!userExist.isPresent()) {
			throw new RuntimeException("User doesnt exist");
		}
		else {
		List<UserInfo> user = new ArrayList<UserInfo>();
		userRepository.findAll().forEach(user::add);
		return user;
		}

	}

	public UserInfo getId(int id) {
		return userRepository.findById(id).get();

	}

	public void deleteUserById(int id) {
		userRepository.deleteById(id);

	}



	
	public Response login(LoginDto logindto) throws UnsupportedEncodingException { // UserInfo userInfo = //
																					// modelMapper.map(logindto, //
																					// UserInfo.class);
		Optional<UserInfo> user = userRepository.findByEmailid(logindto.getEmailid());
		
		System.out.println("user"+user);

		if (!user.isPresent()) {
			throw new RuntimeException("User doesnt exist");
		}
		if (!user.get().getPassword().equals(logindto.getPassword())) {

			throw new RuntimeException("Password mismatch");

		}
		String userToken = tokenService.createToken(user.get().getEmailid());
		System.out.println(userToken);
		jms.sendMail(logindto.getEmailid(), userToken,"welcome");
		

		return new Response(200, "Login Successfull", userToken);

	}
	 
	 

	
	public Response forgetPass(LoginDto loginDto,String token) throws UnsupportedEncodingException {

		String tok	=	tokenService.getUserToken(token);
		
		Optional<UserInfo> userExist = userRepository.findByEmailid(tok);
		System.out.println("user"+userExist);


		if (userExist.isPresent()) {
			
			
			jms.sendMail(loginDto.getEmailid(), tok,"http://localhost:8080/resetPassword");

			return new Response(200, "Successful", null);
		} else {
			return new Response(200, "USerDoesnt exist", null);
		}

	}

	public Response resetPass(ResetPasswordDto resetPasswordDto, String token) {
		
		String emailid = tokenService.getUserToken(token);
		System.out.println(emailid);

		//UserInfo userInfo = modelMapper.map(resetPasswordDto, UserInfo.class);
		Optional<UserInfo> userExist = userRepository.findByEmailid(emailid);
		
		
			if (resetPasswordDto.getPassword().equals(resetPasswordDto.getConfirmPassword())) {
				userExist.get().setPassword(resetPasswordDto.getPassword());
				userExist.get().setConfirmPassword(resetPasswordDto.getPassword());

				userRepository.save(userExist.get());
				return new Response(200, "Successful", null);
			}
		

		else if (userExist == null) {
			throw new RuntimeException("Password mismatch");

		}

		return new Response(500, "Password mismatch", "null");

	}

	public void updateUser( UserDto userDto,String token) {
		String emailid = tokenService.getUserToken(token);
		Optional<UserInfo> user	=	userRepository.findByEmailid(emailid);
		if(user.isPresent()) {
			user.get().setMobileNo(userDto.getMobileNo());
			user.get().setName(userDto.getName());
			userRepository.save(user.get());
		}

		
	}

}
