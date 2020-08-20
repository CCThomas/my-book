package com.github.ccthomas.mybook.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.github.ccthomas.mybook.models.user.User;
import com.github.ccthomas.mybook.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

public class UserControllerTest {

    @InjectMocks
    UserController userController;

    @Mock
    User user;

    @Mock
    UserService userService;

    long id;

    private final String username = "username";

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUsername(username);
    }

    @Test
    public void delete() {
        // exercise
        HttpStatus actual = userController.deleteById(id);

        // verify
        assertEquals(HttpStatus.OK, actual);
        verify(userService).deleteById(id);
    }

    @Test
    public void findById() {
        // setup
        when(userService.findById(id)).thenReturn(user);

        // exercise
        User actual = userController.findById(id);

        // verify
        assertEquals(user, actual);
        verify(userService).findById(id);
    }

    @Test
    public void findByUsername() {
        // setup
        when(userService.findByUsername(username)).thenReturn(user);

        // exercise
        User actual = userController.findByUsername(username);

        // verify
        assertEquals(user, actual);
        verify(userService).findByUsername(username);
    }

    @Test
    public void save() {
        // setup
        when(userService.save(user)).thenReturn(user);

        // exercise
        User actual = userController.save(user);

        // verify
        assertEquals(user, actual);
        verify(userService).save(user);
    }
}