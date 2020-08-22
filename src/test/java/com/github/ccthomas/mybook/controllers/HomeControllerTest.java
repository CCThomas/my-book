package com.github.ccthomas.mybook.controllers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HomeControllerTest {

	HomeController homeController;

	@Test
	public void index() {
		// setup
		homeController = new HomeController();

		// exercise & verify
		assertEquals("Welcome to myBook!", homeController.index(null));
	}

	@Test
	public void index_username_provided() {
		// setup
		homeController = new HomeController();
		String username = "username";

		// exercise & verify
		assertEquals("Welcome to myBook username!", homeController.index(username));
	}

	@Test
	public void index_username_empty() {
		// setup
		homeController = new HomeController();

		// exercise & verify
		assertEquals("Welcome to myBook!", homeController.index(""));
	}

	@Test
	public void index_username_blank() {
		// setup
		homeController = new HomeController();

		// exercise & verify
		assertEquals("Welcome to myBook!", homeController.index(" "));
	}

}
