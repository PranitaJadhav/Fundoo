package com.bridgelabz.demo.userservice;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.OpOr;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.demo.dto.ForgetPasswordDto;
import com.bridgelabz.demo.dto.LoginDto;
import com.bridgelabz.demo.dto.ResetPasswordDto;
import com.bridgelabz.demo.dto.UserDto;
import com.bridgelabz.demo.exception.Response;
import com.bridgelabz.demo.model.User;
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
	/*
	 * @Autowired BCryptPasswordEncoder bycryptPasswordEncoder;
	 */
	// List<User> user = new ArrayList<User>();

	public Response addUser(UserDto userdto) {
		User userInfo = modelMapper.map(userdto, User.class);
		Optional<User> userExist = userRepository.findByEmailid(userdto.getEmailid());

		if (userExist.isPresent()) {
			return new Response(200, "User Exist", null);

		} else {

			userInfo.setPassword(userdto.getPassword());

			String message = "Registered Successfully";
			System.out.println(message); //
			// jms.sendMail(userdto.getEmailid(), null,message);

			userRepository.save(userInfo);
			return new Response(200, "Registered", null);

			// String user = userExist.get().getName();

		}

	}

	public List<User> getAll(String token) {
		String tok = tokenService.getUserToken(token);
		Optional<User> userExist = userRepository.findByEmailid(tok);

		if (!userExist.isPresent()) {
			throw new RuntimeException("User doesnt exist");
		} else {
			List<User> user = new ArrayList<User>();
			userRepository.findAll().forEach(user::add);
			return user;
		}

	}

	public User getId(int id) {
		return userRepository.findById(id).get();

	}

	public void deleteUserById(int id) {
		userRepository.deleteById(id);

	}

	public Response login(LoginDto logindto) throws UnsupportedEncodingException {
		// User user = modelMapper.map(logindto, User.class);
		Optional<User> userExist = userRepository.findByEmailid(logindto.getEmailid());

		// System.out.println("user" + user);
		String userToken = null;

		if (!userExist.isPresent()) {
			return new Response(200, "User does not Exist", null);

		}
		// if (!user.get().getPassword().equals(logindto.getPassword())) {

		if (!userExist.get().getPassword().equals((logindto.getPassword()))) {

			userToken = tokenService.createToken(userExist.get().getEmailid());

		}

		// System.out.println(userToken);
		// jms.sendMail(logindto.getEmailid(), userToken,"welcome");

		return new Response(200, "Login Successfull", userToken);

	}

	public Response forgetPass(ForgetPasswordDto forgetPasswordDto) /* throws UnsupportedEncodingException */ {

		// String tok = tokenService.getUserToken(token);

		Optional<User> userExist = userRepository.findByEmailid(forgetPasswordDto.getEmailid());
		System.out.println("user" + userExist);

		if (userExist.isPresent()) {

			jms.sendMail(forgetPasswordDto.getEmailid(), null, "http://localhost:8080/resetPassword");

			return new Response(200, "Successful", null);
		} else {
			return new Response(200, "USerDoesnt exist", null);
		}

	}

	public Response resetPass(ResetPasswordDto resetPasswordDto) {

		/*
		 * String emailid = tokenService.getUserToken(token);
		 * System.out.println(emailid);
		 */

		// UserInfo userInfo = modelMapper.map(resetPasswordDto, UserInfo.class);
		Optional<User> userExist = userRepository.findByEmailid(resetPasswordDto.getEmailid());

		if (resetPasswordDto.getPassword().equals(resetPasswordDto.getConfirmPassword())) {
			userExist.get().setPassword(resetPasswordDto.getPassword());
			userExist.get().setConfirmPassword(resetPasswordDto.getPassword());

			userRepository.save(userExist.get());
			return new Response(200, "Successful", null);
		}

		else if (userExist == null) {
			// throw new RuntimeException("Password mismatch");

		}

		return new Response(500, "Password mismatch", "null");

	}

	public void updateUser(UserDto userDto, String token) {
		String emailid = tokenService.getUserToken(token);
		Optional<User> user = userRepository.findByEmailid(emailid);
		if (user.isPresent()) {
			user.get().setMobileNo(userDto.getMobileNo());
			user.get().setName(userDto.getName());
			userRepository.save(user.get());
		}

	}

}
