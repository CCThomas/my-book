package com.github.ccthomas.mybook.controllers;

import com.github.ccthomas.mybook.models.user.Role;
import com.github.ccthomas.mybook.models.user.User;
import com.github.ccthomas.mybook.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	Role role;

	@Mock
	User user;

	@Mock
	UserService userService;

	long id;

	private final String email = "email";

	private final String provider = "provider";

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
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
	public void deleteRole() {
		// exercise
		HttpStatus actual = userController.deleteRoleById(id);

		// verify
		assertEquals(HttpStatus.OK, actual);
		verify(userService).deleteRoleById(id);
	}

	@Test
	public void findRoleAll() {
		// setup
		when(userService.findRoleAll()).thenReturn(List.of(role));

		// exercise
		List<Role> actual = userController.findRoleAll();

		// verify
		assertEquals(List.of(role), actual);
		verify(userService).findRoleAll();
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
	public void findByEmailAndProvider() {
		// setup
		when(userService.findByEmailAndProvider(email, provider)).thenReturn(user);

		// exercise
		User actual = userController.findByEmailAndProvider(email, provider);

		// verify
		assertEquals(user, actual);
		verify(userService).findByEmailAndProvider(email, provider);
	}

	@Test
	public void saveRole() {
		// setup
		when(userService.saveRole(role)).thenReturn(role);

		// exercise
		Role actual = userController.saveRole(role);

		// verify
		assertEquals(role, actual);
		verify(userService).saveRole(role);
	}

}