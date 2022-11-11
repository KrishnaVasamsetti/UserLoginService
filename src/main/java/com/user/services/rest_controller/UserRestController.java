package com.user.services.rest_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.services.model.User;
import com.user.services.service.UserService;

@RestController
@RequestMapping("user")
public class UserRestController {
	
	@Autowired
	UserService userService;

	@GetMapping("/info")
	public String userInfo() {
		return "Welcome to user information service";
	}
	
	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userService.getUserList();
	}
	
	@GetMapping("add") 
	public String addUser(){
		User user = new User();
		user.id = 4;
		user.name = "Krishna";
		User createdUser = userService.addNewUser(user);
		return createdUser.name;
	}
}
