package com.github.ccthomas.mybook.repository;

import com.github.ccthomas.mybook.models.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA Repository for {@link Book}
 *
 * @author CCThomas
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
