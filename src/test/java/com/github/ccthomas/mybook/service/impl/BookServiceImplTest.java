package com.github.ccthomas.mybook.service.impl;


import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BookServiceImplTest {

    @InjectMocks
    BookServiceImpl bookServiceImpl;

    @Mock
    Book book;

    @Mock
    BookRepository bookRepository;

    private final Long id = 1l;
    private final String bookname = "bookname";

    @Before
    public void before(){
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
    public void save() {
        // setup
        when(bookRepository.save(book)).thenReturn(book);

        // exercise
        Book actual = bookServiceImpl.save(book);

        // verify
        assertEquals(book, actual);
    }
}
