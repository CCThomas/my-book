package com.github.ccthomas.mybook.models.association;

import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.models.user.Role;
import com.github.ccthomas.mybook.models.user.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BookToUserTest {

	@Mock
	Book book;

	@Mock
	User user;

	@Mock
	Role role;

	final Long id = 1L;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getSetId() {
		// setup
		BookToUser bookToUser = new BookToUser();

		// exercise
		bookToUser.setId(id);

		// verify
		assertEquals(id, bookToUser.getId());
	}

	@Test
	public void getSetBook() {
		// setup
		BookToUser bookToUser = new BookToUser();

		// exercise
		bookToUser.setBook(book);

		// verify
		assertEquals(book, bookToUser.getBook());
	}

	@Test
	public void getSetUser() {
		// setup
		BookToUser bookToUser = new BookToUser();

		// exercise
		bookToUser.setUser(user);

		// verify
		assertEquals(user, bookToUser.getUser());
	}

	@Test
	public void getSetRole() {
		// setup
		BookToUser bookToUser = new BookToUser();

		// exercise
		bookToUser.setRole(role);

		// verify
		assertEquals(role, bookToUser.getRole());
	}

	@Test
	public void toString_empty() {
		// setup
		String expected = "BookToUser{id=null, book=null, user=null, role=null}";
		BookToUser bookToUser = new BookToUser();

		// exercise & verify
		assertEquals(expected, bookToUser.toString());
	}

	@Test
	public void toString_populated() {
		// setup
		String expected = "BookToUser{id=1, book=book, user=user, role=role}";
		BookToUser bookToUser = new BookToUser();
		bookToUser.setId(id);
		bookToUser.setBook(book);
		bookToUser.setUser(user);
		bookToUser.setRole(role);
		when(book.toString()).thenReturn("book");
		when(user.toString()).thenReturn("user");
		when(role.toString()).thenReturn("role");

		// exercise & verify
		assertEquals(expected, bookToUser.toString());
	}

}
