package com.bridgelabz.demo.userservice;

import java.util.List;

import com.bridgelabz.demo.dto.UserDto;
import com.bridgelabz.demo.exception.Response;
import com.bridgelabz.demo.model.User;

public interface Service {
	public Response addUser(UserDto userdto);
	public List<User> getAll();

}
