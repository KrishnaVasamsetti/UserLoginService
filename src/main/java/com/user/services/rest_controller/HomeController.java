package com.user.services.rest_controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HomeController {

	@GetMapping
	public String homePage() {
		return "<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "\n"
				+ "	<head>\n"
				+ "		<meta charset=\"ISO-8859-1\" />\n"
				+ "		<link href=\"css/styles.css\" rel=\"stylesheet\" />\n"
				+ "		<title>Spring Boot Application</title>\n"
				+ "	</head>\n"
				+ "\n"
				+ "	<body>\n"
				+ "		<h4>Welcome to Employee Spring Boot web application</h4>\n"
				+ "		<a href='/swagger-ui/'>Swagger UI docs</a>\n"
				+ "		<br>\n"
				+ "		<a href='/v2/api-docs'>Api Docs</a>\n"
				+ "		<br>\n"
				+ "		<a href='/user/info'>Goto Website</a>\n"
				+ "	</body>\n"
				+ "\n"
				+ "</html>";
	}
}
