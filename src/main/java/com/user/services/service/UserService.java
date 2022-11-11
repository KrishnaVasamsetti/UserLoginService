package com.user.services.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.services.model.User;
import com.user.services.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
	public List<User> getUserList()  {
		List<User> userList = new ArrayList<User>();
		Iterator<User> iterator = userRepo.findAll().iterator();
		while(iterator.hasNext()) {
			User item = iterator.next();
			userList.add(item);
		}
		return userList;
	}
	
	public User addNewUser(User user) {
		User createdUser = userRepo.save(user);
		return createdUser;
	}

}
