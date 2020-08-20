package com.github.ccthomas.mybook.models.user;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RoleTest {

    private final Long id = 1l;
    private final String title = "title";

    @Test
    public void getSetId() {
        // setup
        Role role = new Role();

        // exercise
        role.setId(id);

        // verify
        assertEquals(id, role.getId());
    }

    @Test
    public void getSetRolename() {
        // setup
        Role role = new Role();

        // exercise
        role.setTitle(title);

        // verify
        assertEquals(title, role.getTitle());
    }

    @Test
    public void toString_empty() {
        // setup
        String expected = "Role{id=null, title='null'}";
        Role role = new Role();

        // exercise & verify
        assertEquals(expected, role.toString());
    }

    @Test
    public void toString_populated() {
        // setup
        String expected = "Role{id=1, title='title'}";
        Role role = new Role();
        role.setId(id);
        role.setTitle(title);

        // exercise & verify
        assertEquals(expected, role.toString());
    }
}
