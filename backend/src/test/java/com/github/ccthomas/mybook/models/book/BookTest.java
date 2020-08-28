package com.github.ccthomas.mybook.models.book;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BookTest {

	@Mock
	ExternalLink externalLink1;

	@Mock
	ExternalLink externalLink2;

	private final Long id = 1l;

	private final String title = "title";

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getSetId() {
		// setup
		Book book = new Book();

		// exercise
		book.setId(id);

		// verify
		assertEquals(id, book.getId());
	}

	@Test
	public void getSetTitle() {
		// setup
		Book book = new Book();

		// exercise
		book.setTitle(title);

		// verify
		assertEquals(title, book.getTitle());
	}

	@Test
	public void getSetExternalLinks() {
		// setup
		Book book = new Book();

		// exercise
		book.setExternalLinks(List.of(externalLink1));

		// verify
		assertEquals(List.of(externalLink1), book.getExternalLinks());
	}

	@Test
	public void toString_empty() {
		// setup
		String expected = "Book{id=null, title='null', externalLinks=null}";
		Book book = new Book();

		// exercise & verify
		assertEquals(expected, book.toString());
	}

	@Test
	public void toString_populated() {
		// setup
		String expected = "Book{id=1, title='title', externalLinks=[externalLink1,externalLink2]}";
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setExternalLinks(List.of(externalLink1, externalLink2));
		when(externalLink1.toString()).thenReturn("externalLink1");
		when(externalLink2.toString()).thenReturn("externalLink2");

		// exercise & verify
		assertEquals(expected, book.toString());
	}

}
