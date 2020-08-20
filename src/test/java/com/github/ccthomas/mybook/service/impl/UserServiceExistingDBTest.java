package com.github.ccthomas.mybook.service.impl;

import com.github.ccthomas.mybook.models.user.User;
import com.github.ccthomas.mybook.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceExistingDBTest {
    @InjectMocks
    UserServiceExistingDB userServiceExistingDB;

    @Mock
    User user;

    @Mock
    User existingUser;

    @Mock
    UserRepository userRepository;

    private final Long id = 1l;
    private final Long existingId = 2l;
    private final String username = "username";
    private final String existingUsername = "existingUsername";

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        when(user.getId()).thenReturn(null);
        when(existingUser.getId()).thenReturn(existingId);
        when(existingUser.getUsername()).thenReturn((existingUsername));
    }

    @Test
    public void deleteById() {
        // exercise
        userServiceExistingDB.deleteById(id);

        // verify
        verify(userRepository).deleteById(id);
    }

    @Test
    public void findById() {
        // setup
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // exercise
        User actual = userServiceExistingDB.findById(id);

        // verify
        assertEquals(user, actual);
    }

    @Test
    public void findById_notFound() {
        // setup
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // exercise & verify
        assertNull(userServiceExistingDB.findById(id));
    }

    @Test
    public void findByUsername() {
        // setup
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // exercise
        User actual = userServiceExistingDB.findByUsername(username);

        // verify
        assertEquals(user, actual);
    }

    @Test
    public void findByUsername_notFound() {
        // setup
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // exercise & verify
        assertNull(userServiceExistingDB.findByUsername(username));
    }

    @Test
    public void save() {
        // setup
        when(user.getId()).thenReturn(null);
        when(user.getUsername()).thenReturn(username);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());

        long previousGeneratedId = 5l;
        userServiceExistingDB.previousGeneratedId = previousGeneratedId;

        when(userRepository.save(user)).thenReturn(user);

        // exercise
        User actual = userServiceExistingDB.save(user);

        // verify
        assertEquals(user, actual);
        verify(user).setId(previousGeneratedId + 1);
    }

    @Test
    public void save_user_null() {
        // setup
        String expectedMessage = "User is null";

        try {
            // exercise
            userServiceExistingDB.save(null);
            fail("Exception should have been thrown with message=" + expectedMessage);
        } catch (IllegalStateException e) {
            // verify
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void save_userId_notNull() {
        // setup
        when(user.getId()).thenReturn(id);
        when(userRepository.save(user)).thenReturn(user);

        // exercise
        User actual = userServiceExistingDB.save(user);

        // verify
        assertEquals(user, actual);
        verify(user, times(0)).setId(anyLong());
    }

    @Test
    public void save_username_null() {
        // setup
        String expectedMessage = "Username is null or blank. username=null";
        when(user.getUsername()).thenReturn(null);

        try {
            // exercise
            userServiceExistingDB.save(user);
            fail("Exception should have been thrown with message=" + expectedMessage);
        } catch (IllegalStateException e) {
            // verify
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void save_username_empty() {
        // setup
        String expectedMessage = "Username is null or blank. username=";
        when(user.getUsername()).thenReturn("");

        try {
            // exercise
            userServiceExistingDB.save(user);
            fail("Exception should have been thrown with message=" + expectedMessage);
        } catch (IllegalStateException e) {
            // verify
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void save_username_blank() {
        // setup
        String expectedMessage = "Username is null or blank. username= ";
        when(user.getUsername()).thenReturn(" ");

        try {
            // exercise
            userServiceExistingDB.save(user);
            fail("Exception should have been thrown with message=" + expectedMessage);
        } catch (IllegalStateException e) {
            // verify
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void save_username_isPresent() {
        // setup
        String expectedMessage = "Username already exists";
        when(user.getId()).thenReturn(null);
        when(user.getUsername()).thenReturn(username);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(existingUser));

        try {
            // exercise
            userServiceExistingDB.save(user);
            fail("Exception should have been thrown with message=" + expectedMessage);
        } catch (IllegalStateException e) {
            // verify
            assertEquals(expectedMessage, e.getMessage());
        }
    }

    @Test
    public void save_previousGeneratedId_null_existingUserIsPresent() {
        // setup
        when(user.getId()).thenReturn(null);
        when(user.getUsername()).thenReturn(username);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());

        userServiceExistingDB.previousGeneratedId = null;
        when(userRepository.findFirstByOrderByIdDesc()).thenReturn(Optional.of(existingUser));

        when(userRepository.save(user)).thenReturn(user);

        // exercise
        User actual = userServiceExistingDB.save(user);

        // verify
        assertEquals(user, actual);
        verify(user).setId(existingId + 1);
    }

    @Test
    public void save_previousGeneratedId_null_existingUserIsEmpty() {
        // setup
        when(user.getId()).thenReturn(null);
        when(user.getUsername()).thenReturn(username);
        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());

        userServiceExistingDB.previousGeneratedId = null;
        when(userRepository.findFirstByOrderByIdDesc()).thenReturn(Optional.empty());

        when(userRepository.save(user)).thenReturn(user);

        // exercise
        User actual = userServiceExistingDB.save(user);

        // verify
        assertEquals(user, actual);
        verify(user).setId(userServiceExistingDB.STARTING_ID + 1);
    }
}
