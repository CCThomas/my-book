package com.github.ccthomas.mybook.service.impl;

import com.github.ccthomas.mybook.models.user.Role;
import com.github.ccthomas.mybook.models.user.User;
import com.github.ccthomas.mybook.repository.RoleRepository;
import com.github.ccthomas.mybook.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UserServiceAuth0Test {

	@InjectMocks
	UserServiceAuth0 userServiceAuth0;

	@Mock
	Role role;

	@Mock
	User user;

	@Mock
	User user2;

	@Mock
	RoleRepository roleRepository;

	@Mock
	UserRepository userRepository;

	private final Long id = 1l;

	private final Long roleId = 2l;

	private final String email = "email";

	private final String provider = "provider";

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deleteById() {
		// exercise
		userServiceAuth0.deleteById(id);

		// verify
		verify(userRepository).deleteById(id);
	}

	@Test
	public void deleteRoleById() {
		// exercise
		userServiceAuth0.deleteRoleById(id);

		// verify
		verify(roleRepository).deleteById(id);
	}

	@Test
	public void findRoleAll() {
		// setup
		when(roleRepository.findAll()).thenReturn(List.of(role));

		// exercise
		List<Role> actual = userServiceAuth0.findRoleAll();

		// verify
		assertEquals(List.of(role), actual);
	}

	@Test
	public void findById() {
		// setup
		when(userRepository.findById(id)).thenReturn(Optional.of(user));

		// exercise
		User actual = userServiceAuth0.findById(id);

		// verify
		assertEquals(user, actual);
	}

	@Test
	public void findById_notFound() {
		// setup
		when(userRepository.findById(id)).thenReturn(Optional.empty());

		// exercise & verify
		assertNull(userServiceAuth0.findById(id));
	}

	@Test
	public void findByEmailAndProvider() {
		// setup
		when(userRepository.findAllByEmailAndProvider(email, provider)).thenReturn(List.of(user));

		// exercise & verify
		assertEquals(user, userServiceAuth0.findByEmailAndProvider(email, provider));
		verify(userRepository, times(0)).save(any());
	}

	@Test
	public void findByEmailAndProvider_emailNull() {
		// setup
		String expectedMessage = "Email or Provider is null, empty, or blank. email=null and provider=provider";

		// exercise
		try {
			userServiceAuth0.findByEmailAndProvider(null, provider);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void findByEmailAndProvider_emailEmpty() {
		// setup
		String expectedMessage = "Email or Provider is null, empty, or blank. email= and provider=provider";

		// exercise
		try {
			userServiceAuth0.findByEmailAndProvider("", provider);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void findByEmailAndProvider_emailBlank() {
		// setup
		String expectedMessage = "Email or Provider is null, empty, or blank. email=  and provider=provider";

		// exercise
		try {
			userServiceAuth0.findByEmailAndProvider(" ", provider);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void findByEmailAndProvider_providerNull() {
		// setup
		String expectedMessage = "Email or Provider is null, empty, or blank. email=email and provider=null";

		// exercise
		try {
			userServiceAuth0.findByEmailAndProvider(email, null);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void findByEmailAndProvider_providerEmpty() {
		// setup
		String expectedMessage = "Email or Provider is null, empty, or blank. email=email and provider=";

		// exercise
		try {
			userServiceAuth0.findByEmailAndProvider(email, "");
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void findByEmailAndProvider_providerBlank() {
		// setup
		String expectedMessage = "Email or Provider is null, empty, or blank. email=email and provider= ";

		// exercise
		try {
			userServiceAuth0.findByEmailAndProvider(email, " ");
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void findByEmailAndProvider_usersEmpty() {
		// setup
		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		when(userRepository.findAllByEmailAndProvider(email, provider)).thenReturn(new ArrayList<>());
		when(userRepository.save(any())).thenReturn(user);

		// exercise & verify
		assertEquals(user, userServiceAuth0.findByEmailAndProvider(email, provider));
		verify(userRepository).save(captor.capture());
		User actualUser = captor.getValue();
		assertEquals(email, actualUser.getEmail());
		assertEquals(provider, actualUser.getProvider());
	}

	@Test
	public void findByEmailAndProvider_userSizeGreaterThanOne() {
		// setup
		String expectedMessage = "Multiple Users exist for email=email and provider=provider";
		when(userRepository.findAllByEmailAndProvider(email, provider)).thenReturn(List.of(user, user2));

		// exercise
		try {
			userServiceAuth0.findByEmailAndProvider(email, provider);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void findRoleById() {
		// setup
		when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

		// exercise
		Role actual = userServiceAuth0.findRoleById(roleId);

		// verify
		assertEquals(role, actual);
	}

	@Test
	public void findRoleById_notFound() {
		// setup
		when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

		// exercise & verify
		assertNull(userServiceAuth0.findRoleById(id));
	}

	@Test
	public void saveRole() {
		// setup
		when(roleRepository.save(role)).thenReturn(role);

		// exercise
		Role actual = userServiceAuth0.saveRole(role);

		// verify
		assertEquals(role, actual);
	}

	@Test
	public void createUser() {

	}

}
