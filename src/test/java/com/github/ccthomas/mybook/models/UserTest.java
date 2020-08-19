package com.github.ccthomas.mybook.models;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private final Long id = 1l;
    private final String username = "username";

    @Test
    public void getSetId() {
        // setup
        User user = new User();

        // exercise
        user.setId(id);

        // verify
        assertEquals(id, user.getId());
    }

    @Test
    public void getSetUsername() {
        // setup
        User user = new User();

        // exercise
        user.setUsername(username);

        // verify
        assertEquals(username, user.getUsername());
    }

    @Test
    public void toString_empty() {
        // setup
        String expected = "User{id=null, username='null'}";
        User user = new User();

        // exercise & verify
        assertEquals(expected, user.toString());
    }

    @Test
    public void toString_populated() {
        // setup
        String expected = "User{id=1, username='username'}";
        User user = new User();
        user.setId(id);
        user.setUsername(username);

        // exercise & verify
        assertEquals(expected, user.toString());
    }
}
