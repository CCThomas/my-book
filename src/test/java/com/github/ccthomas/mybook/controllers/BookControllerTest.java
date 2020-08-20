package com.github.ccthomas.mybook.controllers;

import com.github.ccthomas.mybook.models.book.Book;
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
    BookService bookService;

    long id;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        book = new Book();
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