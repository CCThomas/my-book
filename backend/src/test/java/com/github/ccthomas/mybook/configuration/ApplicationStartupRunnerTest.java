package com.github.ccthomas.mybook.configuration;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.ApplicationArguments;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ApplicationStartupRunnerTest {

	@InjectMocks
	ApplicationStartupRunner applicationStartupRunner;

	@Mock
	ApplicationArguments args;

	@Mock
	DatabaseLoader databaseLoader;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void run() throws Exception {
		// setup
		when(args.getSourceArgs()).thenReturn(new String[] {});

		// exercise & verify
		applicationStartupRunner.run(args);
	}

	@Test
	public void run_databaseLoader() throws Exception {
		// setup
		String argument = "argument";
		when(args.getSourceArgs()).thenReturn(new String[] { ApplicationStartupRunner.KEY_DATABASE_LOADER + argument });

		// exercise
		applicationStartupRunner.run(args);

		// verify
		verify(databaseLoader).load(argument);
	}

}
