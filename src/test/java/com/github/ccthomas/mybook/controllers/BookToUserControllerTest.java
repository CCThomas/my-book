package com.github.ccthomas.mybook.controllers;

import com.github.ccthomas.mybook.models.association.BookToUser;
import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.service.BookService;
import com.github.ccthomas.mybook.service.BookToUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class BookToUserControllerTest {

    @InjectMocks
    BookToUserController bookToUserController;

    @Mock
    BookToUser bookToUser;

    @Mock
    BookToUserService bookToUserService;

    private final Long id = 1L;
    private final Long bookId = 2L;
    private final Long userId = 3L;
    private final Long roleId = 4L;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create() {
        // setup
        when(bookToUserService.create(bookId, userId, roleId)).thenReturn(bookToUser);

        // exercise & verify
        assertEquals(bookToUser, bookToUserController.create(bookId, userId, roleId));
    }

    @Test
    public void deleteById() {
        // exercise
        HttpStatus actual = bookToUserController.deleteById(id);

        // verify
        assertEquals(HttpStatus.OK, actual);
    }

    @Test
    public void findAllByBookId() {
        // setup
        when(bookToUserService.findAllByBookId(bookId)).thenReturn(List.of(bookToUser));

        // exercise & verify
        assertEquals(List.of(bookToUser), bookToUserController.findAllByBookId(bookId));
    }

    @Test
    public void findAllByUserId() {
        // setup
        when(bookToUserService.findAllByUserId(userId)).thenReturn(List.of(bookToUser));

        // exercise & verify
        assertEquals(List.of(bookToUser), bookToUserController.findAllByUserId(bookId));
    }
}
