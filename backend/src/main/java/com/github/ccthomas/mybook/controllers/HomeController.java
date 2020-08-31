package com.github.ccthomas.mybook.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HomeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping("/")
	public String index(@RequestParam(required = false) String username) {
		LOGGER.info("root api / hit with username={}", username);
		return "Welcome to myBook" + (username == null || username.isEmpty() ? "" : " " + username) + "!";
	}

}
