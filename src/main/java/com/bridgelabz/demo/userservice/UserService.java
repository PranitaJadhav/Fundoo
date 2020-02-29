package com.bridgelabz.demo.userservice;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
//import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.demo.dto.ForgetPasswordDto;
import com.bridgelabz.demo.dto.LoginDto;
import com.bridgelabz.demo.dto.ResetPasswordDto;
import com.bridgelabz.demo.dto.UserDto;
import com.bridgelabz.demo.exception.Response;
import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.userrepository.UserRepository;
import com.bridgelabz.demo.utility.JMS;
import com.bridgelabz.demo.utility.TokenService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

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
		String emailID = tokenService.getUserToken(token);
		Optional<User> userExist = userRepository.findByEmailid(emailID);

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

		if (userExist.get().getPassword().equals((logindto.getPassword()))) {

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

	/*
	 * public Response uplaodImage(String token, Multipart file) { String emailid =
	 * tokenService.getUserToken(token); Optional<User> user =
	 * userRepository.findByEmailid(emailid); if (user.isPresent()) { UUID uuid =
	 * UUID.randomUUID(); String uniqueId = uuid.toString();
	 * //Files.copy(file.getParent().getInputStream(), path.r, options); }
	 * 
	 * return null; }
	 * 
	 * public Response getImage(String token) { return null; }
	 */

	/*
	 * public Response uplaodImage(String token, MultipartFile file) throws
	 * IOException { String emailid = tokenService.getUserToken(token);
	 * Optional<User> user = userRepository.findByEmailid(emailid); if
	 * (!user.isPresent()) { return new Response(200, "User does not Exist", null);
	 * 
	 * } File uploadFile = new File(file.getOriginalFilename());
	 * BufferedOutputStream outPutStream = new BufferedOutputStream(new
	 * FileOutputStream(uploadFile));
	 * 
	 * outPutStream.write(file.getBytes()); Cloudinary cloudinary = new
	 * Cloudinary(ObjectUtils.asMap(values)); }
	 */

	public Response uploadProPic(String token, MultipartFile file) throws IOException {
		String emailid = tokenService.getUserToken(token);
		Optional<User> user = userRepository.findByEmailid(emailid);
		if (user.isPresent()) {
			System.out.println("hey");
			File uploadFile = new File(file.getOriginalFilename());
			System.out.println(uploadFile);
			BufferedOutputStream outStream = new BufferedOutputStream(new FileOutputStream(uploadFile));
			outStream.write(file.getBytes());
			outStream.close();
			Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "yelloracaves", "api_key",
					"982216137489194", "api_secret", "rsDRuXLXg5n1HTZstmcckBxoOxY"));
			System.out.println(ObjectUtils.emptyMap());
			Map<?, ?> uploadProfile;
			//uploadProfile = cloudinary.uploader().upload(uploadFile, ObjectUtils.emptyMap());
			uploadProfile = cloudinary.uploader().upload(uploadFile, ObjectUtils.emptyMap());
			

			user.get().setImage(uploadProfile.get("secure_url").toString());
			userRepository.save(user.get());
			return new Response(200, "imageUploaded successfully", null);
                                                                                                                                                                                                                                                                                                                                                                                                                                    
		}
		return null;

	}

}
