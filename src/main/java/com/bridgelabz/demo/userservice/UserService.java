package com.bridgelabz.demo.userservice;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

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
	TokenService token;

	@Autowired
	JMS jms;

	List<UserInfo> user = new ArrayList<UserInfo>();

	public Response addUser(UserDto userdto) throws UnsupportedEncodingException {
		System.out.println("hey");
											//source	destination
		UserInfo userInfo = modelMapper.map(userdto, UserInfo.class);
		System.out.println("hey1");

		UserInfo userExist = userRepository.findByEmailid(userdto.getEmailid());
		if (!(userExist == null)) {
			return new Response(200, "User Exist", null);

		}

		else if (userdto.getPassword().equals(userdto.getConfirmPassword())) {
			String userToken;

			userToken = token.createToken(userdto.getEmailid());
			jms.sendMail(userdto.getEmailid(), userToken);
			
			
			userRepository.save(userInfo);
			

		}

		return new Response(500, "Unkonwn Error", null);

	}

	public List<UserInfo> getAll() {
		List<UserInfo> user = new ArrayList<UserInfo>();
		userRepository.findAll().forEach(user::add);
		return user;

	}

	public UserInfo getId(int id) {
		return userRepository.findById(id).get();

	}

	public void deleteUserById(int id) {
		userRepository.deleteById(id);

	}

	public void updateUser(int id, UserDto userdto) {
		UserInfo userInfo = modelMapper.map(userdto, UserInfo.class);

		for (int i = 0; i < user.size(); i++) {
			UserInfo t = user.get(i);
			if (t.getId() == id) {
				user.set(i, userInfo);

				// return "Updated";

			}

		}

	}

	public Response login(LoginDto logindto) throws UnsupportedEncodingException {
		// UserInfo userInfo = modelMapper.map(logindto, UserInfo.class);
		UserInfo user = userRepository.findByEmailid(logindto.getEmailid());

		// user contain the either email id which is present in db and if email
		// not matches then it will contain null
		if (user == null) {
			throw new RuntimeException("User doesnt exist");
		}
		if (!user.getPassword().equals(logindto.getPassword())) {

			throw new RuntimeException("Password mismatch");

		}
		String userToken = token.createToken(logindto.getEmailid());
		System.out.println(userToken);


		return new Response(200, "Login Successfull", null);

	}

	/*
	 * public Response forgetPass(ForgetPasswordDto forgetPasswordDto) throws
	 * UnsupportedEncodingException {
	 * 
	 * UserInfo userExist =
	 * userRepository.findByEmailid(forgetPasswordDto.getEmailid()); if (!(userExist
	 * == null)) {
	 * 
	 * String forgetToken = token.createToken(forgetPasswordDto.getEmailid());
	 * jms.sendMail(forgetPasswordDto.getEmailid(), forgetToken); return new
	 * Response(200, "Successful", null); } else { return new Response(200,
	 * "USerDoesnt exist", null); }
	 */

	//}

	public Response resetPass(ResetPasswordDto resetPasswordDto) {
		UserInfo userInfo = modelMapper.map(resetPasswordDto, UserInfo.class);
		UserInfo userExist = userRepository.findByEmailid(resetPasswordDto.getEmailid());
		if (!(userExist == null)) {
			if (resetPasswordDto.getPassword().equals(resetPasswordDto.getConfirmPassword())) {
				userRepository.save(userInfo);
				return new Response(200, "Successful", null);
			}
		}

		else if (userExist == null) {
			throw new RuntimeException("Password mismatch");

		}

		return new Response(500, "Password mismatch", "null");

	}

}
