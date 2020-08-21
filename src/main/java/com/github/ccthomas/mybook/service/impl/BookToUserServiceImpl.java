package com.github.ccthomas.mybook.service.impl;

import com.github.ccthomas.mybook.models.association.BookToUser;
import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.models.user.Role;
import com.github.ccthomas.mybook.models.user.User;
import com.github.ccthomas.mybook.repository.BookToUserRepository;
import com.github.ccthomas.mybook.service.BookService;
import com.github.ccthomas.mybook.service.BookToUserService;
import com.github.ccthomas.mybook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookToUserServiceImpl implements BookToUserService {
    private static Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    BookToUserRepository bookToUserRepository;

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @Override
    public BookToUser create(long bookId, long userId, long roleId) {
        LOGGER.info("Creating book to user with bookId={}, userId={}, and roleId={}", bookId, userId, roleId);

        if (bookToUserRepository.existsByBookIdAndUserIdAndRoleId(bookId, userId, roleId)) {
            throw new IllegalStateException("Book to User Already Exists!");
        }

        Book book = bookService.findById(bookId);
        if (book == null) {
            throw new IllegalStateException("No book exists for id=" + bookId);
        }

        User user = userService.findById(userId);
        if (user == null) {
            throw new IllegalStateException("No user exists for id=" + userId);
        }

        Role role = userService.findRoleById(roleId);
        if (role == null) {
            throw new IllegalStateException("No role exists for id=" + roleId);
        }

        BookToUser bookToUser = new BookToUser();
        bookToUser.setBook(book);
        bookToUser.setUser(user);
        bookToUser.setRole(role);

        LOGGER.info("Saving BookToUser={}", bookToUser);
        return bookToUserRepository.save(bookToUser);
    }

    @Override
    public void deleteById(long id) {
        LOGGER.info("Deleting book with id={}", id);
        bookToUserRepository.deleteById(id);
    }

    @Override
    public List<BookToUser> findAllByBookId(long id) {
        LOGGER.info("Find all book to users by book id={}", id);
        List<BookToUser> bookToUsers = bookToUserRepository.findAllByBookId(id);
        LOGGER.info("returning book to users=", bookToUsers.stream().map(a -> a.toString()).collect(Collectors.joining(",")));
        return bookToUsers;
    }

    @Override
    public List<BookToUser> findAllByUserId(long id) {
        LOGGER.info("Find all book to users by user id={}", id);
        List<BookToUser> bookToUsers = bookToUserRepository.findAllByUserId(id);
        LOGGER.info("returning book to users=", bookToUsers.stream().map(a -> a.toString()).collect(Collectors.joining(",")));
        return bookToUsers;
    }
}
