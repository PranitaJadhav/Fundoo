package com.bridgelabz.demo.userservice;

import java.util.List;

import com.bridgelabz.demo.dto.UserDto;
import com.bridgelabz.demo.model.User;
import com.bridgelabz.demo.response.Response;

public interface Service {
	public Response addUser(UserDto userdto);
	public List<User> getAll();

}
