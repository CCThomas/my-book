package com.github.ccthomas.mybook.service;

import com.github.ccthomas.mybook.models.book.Book;
import org.springframework.stereotype.Service;

/**
 * CRUD Service for managing {@link Book}s
 *
 * @author CCThomas
 */
@Service
public interface BookService {

    /**
     * Delete a {@link Book} by id.
     *
     * @param id {@link Book}'s id.
     */
    void deleteById(long id);

    /**
     * Finds a {@link Book} by id.
     *
     * @param id {@link Book}'s id.
     * @return {@link Book} for given id.
     */
    Book findById(long id);

    /**
     * Saves a {@link Book}.
     *
     * @param Book {@link Book} to persist.
     * @return Persisted {@link Book}.
     */
    Book save(Book Book);

}
