package com.github.ccthomas.mybook.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupRunner implements ApplicationRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartupRunner.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOGGER.info("Application Startup Runner running...");
		LOGGER.info("Reading Application Arguments");
		for (String arg : args.getSourceArgs()) {
			LOGGER.info("Argument: '{}'", arg);
		}
		LOGGER.info("Application Startup Runner stopping...");
	}

}
