package com.github.ccthomas.mybook.service.impl;

import com.github.ccthomas.mybook.models.association.BookToUser;
import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.models.user.Role;
import com.github.ccthomas.mybook.models.user.User;
import com.github.ccthomas.mybook.repository.BookToUserRepository;
import com.github.ccthomas.mybook.service.BookService;
import com.github.ccthomas.mybook.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookToUserServiceImplTest {

	@InjectMocks
	BookToUserServiceImpl bookToUserServiceImpl;

	@Mock
	BookToUser bookToUser;

	@Mock
	Book book;

	@Mock
	User user;

	@Mock
	Role role;

	@Mock
	BookToUserRepository bookToUserRepository;

	@Mock
	BookService bookService;

	@Mock
	UserService userService;

	private final Long id = 1L;

	private final Long bookId = 2L;

	private final Long userId = 3L;

	private final Long roleId = 4L;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		when(bookToUserRepository.existsByBookIdAndUserIdAndRoleId(bookId, userId, roleId)).thenReturn(false);
		when(bookService.findById(bookId)).thenReturn(book);
		when(userService.findById(userId)).thenReturn(user);
		when(userService.findRoleById(roleId)).thenReturn(role);
	}

	@Test
	public void create() {
		// setup
		ArgumentCaptor<BookToUser> captor = ArgumentCaptor.forClass(BookToUser.class);
		when(bookToUserRepository.save(any())).thenReturn(bookToUser);

		// exercise
		BookToUser actual = bookToUserServiceImpl.create(bookId, userId, roleId);

		// verify
		assertEquals(bookToUser, actual);
		verify(bookToUserRepository).save(captor.capture());
		BookToUser bookToUser = captor.getValue();
		assertEquals(book, bookToUser.getBook());
		assertEquals(user, bookToUser.getUser());
		assertEquals(role, bookToUser.getRole());
	}

	@Test
	public void create_alreadyExisting() {
		// setup
		String expectedMessage = "Book to User Already Exists!";
		when(bookToUserRepository.existsByBookIdAndUserIdAndRoleId(bookId, userId, roleId)).thenReturn(true);
		when(bookToUserRepository.save(any())).thenReturn(bookToUser);

		try {
			// exercise
			BookToUser actual = bookToUserServiceImpl.create(bookId, userId, roleId);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void create_book_null() {
		// setup
		String expectedMessage = "No book exists for id=" + bookId;
		when(bookService.findById(bookId)).thenReturn(null);

		try {
			// exercise
			BookToUser actual = bookToUserServiceImpl.create(bookId, userId, roleId);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void create_user_null() {
		// setup
		String expectedMessage = "No user exists for id=" + userId;
		when(userService.findById(userId)).thenReturn(null);

		try {
			// exercise
			BookToUser actual = bookToUserServiceImpl.create(bookId, userId, roleId);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void create_role_null() {
		// setup
		String expectedMessage = "No role exists for id=" + roleId;
		when(userService.findRoleById(roleId)).thenReturn(null);

		try {
			// exercise
			BookToUser actual = bookToUserServiceImpl.create(bookId, userId, roleId);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void deleteById() {
		// exercise
		bookToUserServiceImpl.deleteById(id);

		// verify
		verify(bookToUserRepository).deleteById(id);
	}

	@Test
	public void findAllByBookId() {
		// setup
		when(bookToUserRepository.findAllByBookId(bookId)).thenReturn(List.of(bookToUser));

		// exercise & verify
		assertEquals(List.of(bookToUser), bookToUserServiceImpl.findAllByBookId(bookId));
	}

	@Test
	public void findAllByUserId() {
		// setup
		when(bookToUserRepository.findAllByUserId(userId)).thenReturn(List.of(bookToUser));

		// exercise & verify
		assertEquals(List.of(bookToUser), bookToUserServiceImpl.findAllByUserId(userId));
	}

}
