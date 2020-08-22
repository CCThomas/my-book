package com.github.ccthomas.mybook.controllers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HomeControllerTest {

	HomeController homeController;

	@Test
	public void index() {
		// setup
		homeController = new HomeController();

		// exercise & verify
		assertEquals("Welcome to myBook!", homeController.index());
	}

}
