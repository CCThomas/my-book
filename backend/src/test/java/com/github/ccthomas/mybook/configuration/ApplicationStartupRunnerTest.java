package com.github.ccthomas.mybook.configuration;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.ApplicationArguments;

import static org.mockito.Mockito.when;

public class ApplicationStartupRunnerTest {

    @Mock
    ApplicationArguments args;

    ApplicationStartupRunner applicationStartupRunner;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        applicationStartupRunner = new ApplicationStartupRunner();
    }

    @Test
    public void run() throws Exception {
        // setup
        when(args.getSourceArgs()).thenReturn(new String[]{"Arg 1", "Arg 2"});

        // exercise & verify
        applicationStartupRunner.run(args);
    }
}
