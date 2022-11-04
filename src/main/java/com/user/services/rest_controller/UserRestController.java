package com.user.services.rest_controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserRestController {

	@GetMapping("/info")
	public String userInfo() {
		return "Welcome to user information service";
	}
}
