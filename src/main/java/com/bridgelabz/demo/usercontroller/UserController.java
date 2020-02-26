package com.bridgelabz.demo.usercontroller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.demo.dto.ForgetPasswordDto;
import com.bridgelabz.demo.dto.LoginDto;
import com.bridgelabz.demo.dto.ResetPasswordDto;
import com.bridgelabz.demo.dto.UserDto;
import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.response.Response;
import com.bridgelabz.demo.userrepository.UserRepository;
import com.bridgelabz.demo.userservice.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	UserRepository userrepository;

	Logger log = LoggerFactory.getLogger(this.getClass());

	@PostMapping("/add")
	public Response addTopic(@Valid @RequestBody UserDto user) {
		return userService.addUser(user);
	}

	@GetMapping("/get")
	@RequestMapping(method = RequestMethod.GET, value = "/get")
	public List<User> getUser(@RequestParam String token) {
		System.out.println("Hii");
		return userService.getAll(token);

	}

	@GetMapping("/get/{id}")
	public User getById(@PathVariable int id) {
		return userService.getId(id);

	}

	@DeleteMapping("/delete/{id}")
	public String deleteById(@PathVariable int id) {
		userService.deleteUserById(id);
		return "Deleted";

	}

	@PutMapping("/update")
	public String update(@RequestBody UserDto userDto, @RequestParam String token) {
		userService.updateUser(userDto, token);
		return "Updated";

	}

	@PostMapping("/login")
	public Response login(@RequestBody LoginDto logindto) throws UnsupportedEncodingException {
		log.info("inside add method");

		return userService.login(logindto);

	}

	@PostMapping("/forget")
	public Response forgetPassword(@RequestBody ForgetPasswordDto forgetpasswordDto)
			throws UnsupportedEncodingException {

		return userService.forgetPass(forgetpasswordDto);

	}

	@PostMapping("/reset")
	public Response resetPasswor(@RequestBody ResetPasswordDto resetPasswordDto) {
		return userService.resetPass(resetPasswordDto);

	}

}
