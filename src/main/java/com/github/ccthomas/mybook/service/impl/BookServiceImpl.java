package com.github.ccthomas.mybook.service.impl;

import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.repository.BookRepository;
import com.github.ccthomas.mybook.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private static Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void deleteById(long id) {
        LOGGER.info("Deleting book with id={}", id);
        bookRepository.deleteById(id);
    }

    @Override
    public Book findById(long id) {
        LOGGER.info("Finding Book with id={}", id);
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            LOGGER.info("Book does not exist for id=" + id);
            return null;
        }

        Book Book = optionalBook.get();
        LOGGER.info("returning Book={}", Book);
        return Book;
    }

    @Override
    public Book save(Book Book) {
        LOGGER.info("Saving Book={}", Book);
        return bookRepository.save(Book);
    }
}
