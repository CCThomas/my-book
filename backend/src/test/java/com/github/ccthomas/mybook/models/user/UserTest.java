package com.github.ccthomas.mybook.models.user;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {

	private final Long id = 1l;

	private final String email = "email";

	private final String provider = "provider";

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
	public void getSetEmail() {
		// setup
		User user = new User();

		// exercise
		user.setEmail(email);

		// verify
		assertEquals(email, user.getEmail());
	}

	@Test
	public void getSetProvider() {
		// setup
		User user = new User();

		// exercise
		user.setProvider(provider);

		// verify
		assertEquals(provider, user.getProvider());
	}

	@Test
	public void toString_empty() {
		// setup
		String expected = "User{id=null, email='null', provider='null'}";
		User user = new User();

		// exercise & verify
		assertEquals(expected, user.toString());
	}

	@Test
	public void toString_populated() {
		// setup
		String expected = "User{id=1, email='email', provider='provider'}";
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setProvider(provider);

		// exercise & verify
		assertEquals(expected, user.toString());
	}

}
