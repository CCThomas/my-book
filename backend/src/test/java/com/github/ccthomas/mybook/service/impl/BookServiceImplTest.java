package com.github.ccthomas.mybook.service.impl;

import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.models.book.ExternalLink;
import com.github.ccthomas.mybook.models.user.Role;
import com.github.ccthomas.mybook.repository.BookRepository;
import com.github.ccthomas.mybook.repository.ExternalLinkRepository;
import com.github.ccthomas.mybook.service.UserService;
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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookServiceImplTest {

	@InjectMocks
	BookServiceImpl bookServiceImpl;

	@Mock
	Book book;

	@Mock
	ExternalLink externalLink;

	@Mock
	Role role;

	@Mock
	BookRepository bookRepository;

	@Mock
	ExternalLinkRepository externalLinkRepository;

	@Mock
	UserService userService;

	private final Long id = 1L;

	private final long externalId = 2L;

	private final long roleId = 3L;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void deleteById() {
		// exercise
		bookServiceImpl.deleteById(id);

		// verify
		verify(bookRepository).deleteById(id);
	}

	@Test
	public void findById() {
		// setup
		when(bookRepository.findById(id)).thenReturn(Optional.of(book));

		// exercise
		Book actual = bookServiceImpl.findById(id);

		// verify
		assertEquals(book, actual);
	}

	@Test
	public void findById_notFound() {
		// setup
		when(bookRepository.findById(id)).thenReturn(Optional.empty());

		// exercise & verify
		assertNull(bookServiceImpl.findById(id));
	}

	@Test
	public void roleAccessAdd() {
		// setup
		when(externalLinkRepository.findById(externalId)).thenReturn(Optional.of(externalLink));
		when(userService.findRoleById(roleId)).thenReturn(role);

		List<Role> roleAccess = new ArrayList<>();
		when(externalLink.getRoleAccess()).thenReturn(roleAccess);
		when(externalLinkRepository.save(externalLink)).thenReturn(externalLink);

		// exercise
		ExternalLink actual = bookServiceImpl.roleAccessAdd(externalId, roleId);

		// verify
		assertEquals(externalLink, actual);
		assertEquals(role, roleAccess.get(0));
		verify(externalLinkRepository).save(externalLink);
	}

	@Test
	public void roleAccessAdd_externalLink_null() {
		// setup
		String expectedMessage = "No ExternalLink exists for id=" + externalId;
		when(externalLinkRepository.findById(externalId)).thenReturn(Optional.empty());

		try {
			// exercise
			bookServiceImpl.roleAccessAdd(externalId, roleId);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void roleAccessAdd_role_null() {
		// setup
		String expectedMessage = "No Role exists for id=" + roleId;
		when(externalLinkRepository.findById(externalId)).thenReturn(Optional.of(externalLink));
		when(userService.findRoleById(roleId)).thenReturn(null);

		try {
			// exercise
			bookServiceImpl.roleAccessAdd(externalId, roleId);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void roleAccessAdd_roleAccess_null() {
		// setup
		when(externalLinkRepository.findById(externalId)).thenReturn(Optional.of(externalLink));
		when(userService.findRoleById(roleId)).thenReturn(role);

		ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
		when(externalLink.getRoleAccess()).thenReturn(null);
		when(externalLinkRepository.save(externalLink)).thenReturn(externalLink);

		// exercise
		ExternalLink actual = bookServiceImpl.roleAccessAdd(externalId, roleId);

		// verify
		assertEquals(externalLink, actual);
		verify(externalLink).setRoleAccess(captor.capture());
		assertEquals(role, captor.getValue().get(0));
		verify(externalLinkRepository).save(externalLink);
	}

	@Test
	public void roleAccessRemove() {
		// setup
		when(externalLinkRepository.findById(externalId)).thenReturn(Optional.of(externalLink));
		when(userService.findRoleById(roleId)).thenReturn(role);

		List<Role> roleAccess = new ArrayList<>();
		roleAccess.add(role);
		when(externalLink.getRoleAccess()).thenReturn(roleAccess);
		when(role.getId()).thenReturn(roleId);
		when(externalLinkRepository.save(externalLink)).thenReturn(externalLink);

		// exercise
		ExternalLink actual = bookServiceImpl.roleAccessRemove(externalId, roleId);

		// verify
		assertEquals(externalLink, actual);
		assertTrue(roleAccess.isEmpty());
		verify(role).getId();
	}

	@Test
	public void roleAccessRemove_externalLink_null() {
		// setup
		String expectedMessage = "No ExternalLink exists for id=" + externalId;
		when(externalLinkRepository.findById(externalId)).thenReturn(Optional.empty());

		try {
			// exercise
			bookServiceImpl.roleAccessRemove(externalId, roleId);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void roleAccessRemove_roleAccess_null() {
		// setup
		String expectedMessage = "No Role Access for External Link with id=" + externalId;
		when(externalLinkRepository.findById(externalId)).thenReturn(Optional.of(externalLink));
		when(userService.findRoleById(roleId)).thenReturn(role);
		when(externalLink.getRoleAccess()).thenReturn(null);
		try {
			// exercise
			bookServiceImpl.roleAccessRemove(externalId, roleId);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void roleAccessRemove_roleAccess_empty() {
		// setup
		String expectedMessage = "No Role Access for External Link with id=" + externalId;
		when(externalLinkRepository.findById(externalId)).thenReturn(Optional.of(externalLink));
		when(userService.findRoleById(roleId)).thenReturn(role);
		when(externalLink.getRoleAccess()).thenReturn(new ArrayList<>());
		try {
			// exercise
			bookServiceImpl.roleAccessRemove(externalId, roleId);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void roleAccessRemove_role_doesNotExistWithId() {
		// setup
		String expectedMessage = "No role removed for externalId=" + externalId + " and roleId=" + roleId;
		when(externalLinkRepository.findById(externalId)).thenReturn(Optional.of(externalLink));
		when(userService.findRoleById(roleId)).thenReturn(role);

		List<Role> roleAccess = new ArrayList<>();
		roleAccess.add(role);
		when(externalLink.getRoleAccess()).thenReturn(roleAccess);
		when(role.getId()).thenReturn(roleId + 1);
		when(externalLinkRepository.save(externalLink)).thenReturn(externalLink);

		try {
			// exercise
			bookServiceImpl.roleAccessRemove(externalId, roleId);
			fail("Exception should have been thrown with message=" + expectedMessage);
		}
		catch (IllegalStateException e) {
			// verify
			assertEquals(expectedMessage, e.getMessage());
		}
	}

	@Test
	public void save() {
		// setup
		when(bookRepository.save(book)).thenReturn(book);

		// exercise
		Book actual = bookServiceImpl.save(book);

		// verify
		assertEquals(book, actual);
	}

	// =============================
	// === Test Internal Methods ===
	// =============================

	@Test
	public void findExternalLinkById() {
		// setup
		when(externalLinkRepository.findById(externalId)).thenReturn(Optional.of(externalLink));

		// exercise
		ExternalLink actual = bookServiceImpl.findExternalLinkById(externalId);

		// verify
		assertEquals(externalLink, actual);
	}

	@Test
	public void findExternalLinkById_notFound() {
		// setup
		when(externalLinkRepository.findById(externalId)).thenReturn(Optional.empty());

		// exercise & verify
		assertNull(bookServiceImpl.findExternalLinkById(externalId));
	}

}
