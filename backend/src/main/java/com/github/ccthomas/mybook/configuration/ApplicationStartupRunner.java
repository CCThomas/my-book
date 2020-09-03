package com.github.ccthomas.mybook.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Manages Application Startup
 *
 * @author CCThomas
 */
@Component
public class ApplicationStartupRunner implements ApplicationRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartupRunner.class);

	public static final String KEY_DATABASE_LOADER = "--database-loader=";

	@Autowired
	DatabaseLoader databaseLoader;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOGGER.info("Application Startup Runner running...");
		LOGGER.info("Reading Application Arguments");
		for (String arg : args.getSourceArgs()) {
			LOGGER.info("Argument={}", arg);
			if (arg.contains(KEY_DATABASE_LOADER)) {
				LOGGER.info("Database Loader Key found");
				databaseLoader.load(arg.replace(KEY_DATABASE_LOADER, ""));
			}
		}
		LOGGER.info("Application Startup Runner finished...");
	}

}
