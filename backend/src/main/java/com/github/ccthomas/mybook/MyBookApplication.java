package com.github.ccthomas.mybook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyBookApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyBookApplication.class);

	@Autowired
	private static ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(MyBookApplication.class, args);
	}

}
