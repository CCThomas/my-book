package com.github.ccthomas.mybook.service.impl;


import com.github.ccthomas.mybook.models.user.Role;
import com.github.ccthomas.mybook.models.user.User;
import com.github.ccthomas.mybook.repository.RoleRepository;
import com.github.ccthomas.mybook.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userServiceImpl;

    @Mock
    Role role;

    @Mock
    User user;

    @Mock
    RoleRepository roleRepository;

    @Mock
    UserRepository userRepository;

    private final Long id = 1l;
    private final String username = "username";

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deleteById() {
        // exercise
        userServiceImpl.deleteById(id);

        // verify
        verify(userRepository).deleteById(id);
    }

    @Test
    public void deleteRoleById() {
        // exercise
        userServiceImpl.deleteRoleById(id);

        // verify
        verify(roleRepository).deleteById(id);
    }

    @Test
    public void findRoleAll() {
        // setup
        when(roleRepository.findAll()).thenReturn(List.of(role));

        // exercise
        List<Role> actual = userServiceImpl.findRoleAll();

        // verify
        assertEquals(List.of(role), actual);
    }

    @Test
    public void findById() {
        // setup
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // exercise
        User actual = userServiceImpl.findById(id);

        // verify
        assertEquals(user, actual);
    }

    @Test
    public void findById_notFound() {
        // setup
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        // exercise & verify
        assertNull(userServiceImpl.findById(id));
    }

    @Test
    public void findByUsername() {
        // setup
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // exercise
        User actual = userServiceImpl.findByUsername(username);

        // verify
        assertEquals(user, actual);
    }

    @Test
    public void findByUsername_notFound() {
        // setup
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        // exercise & verify
        assertNull(userServiceImpl.findByUsername(username));
    }

    @Test
    public void save() {
        // setup
        when(userRepository.save(user)).thenReturn(user);

        // exercise
        User actual = userServiceImpl.save(user);

        // verify
        assertEquals(user, actual);
    }

    @Test
    public void saveRole() {
        // setup
        when(roleRepository.save(role)).thenReturn(role);

        // exercise
        Role actual = userServiceImpl.saveRole(role);

        // verify
        assertEquals(role, actual);
    }
}
