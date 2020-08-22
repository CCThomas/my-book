package com.github.ccthomas.mybook.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@RequestMapping("/")
	public String index(@RequestParam(required = false) String username) {
		return "Welcome to myBook" + (username == null || username.isBlank() ? "" : " " + username) + "!";
	}

}
