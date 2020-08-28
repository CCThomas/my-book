package com.github.ccthomas.mybook.controllers;

import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.models.book.ExternalLink;
import com.github.ccthomas.mybook.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookControllerTest {

	@InjectMocks
	BookController bookController;

	@Mock
	Book book;

	@Mock
	ExternalLink externalLink;

	@Mock
	BookService bookService;

	long id = 1L;

	long externalId = 2L;

	long roleId = 3L;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void delete() {
		// exercise
		HttpStatus actual = bookController.deleteById(id);

		// verify
		assertEquals(HttpStatus.OK, actual);
		verify(bookService).deleteById(id);
	}

	@Test
	public void findById() {
		// setup
		when(bookService.findById(id)).thenReturn(book);

		// exercise
		Book actual = bookController.findById(id);

		// verify
		assertEquals(book, actual);
		verify(bookService).findById(id);
	}

	@Test
	public void roleAccessAdd() {
		// setup
		when(bookService.roleAccessAdd(externalId, roleId)).thenReturn(externalLink);

		// exercise
		ExternalLink actual = bookController.roleAccessAdd(externalId, roleId);

		// verify
		assertEquals(externalLink, actual);
	}

	@Test
	public void roleAccessRemove() {
		// setup
		when(bookService.roleAccessRemove(externalId, roleId)).thenReturn(externalLink);

		// exercise
		ExternalLink actual = bookController.roleAccessRemove(externalId, roleId);

		// verify
		assertEquals(externalLink, actual);
	}

	@Test
	public void save() {
		// setup
		when(bookService.save(book)).thenReturn(book);

		// exercise
		Book actual = bookController.save(book);

		// verify
		assertEquals(book, actual);
		verify(bookService).save(book);
	}

}