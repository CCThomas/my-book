package com.github.ccthomas.mybook.service;

import com.github.ccthomas.mybook.models.association.BookToUser;
import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.models.user.Role;
import com.github.ccthomas.mybook.models.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing {@link BookToUser}s
 *
 * @author CCThomas
 */
@Service
public interface BookToUserService {

	/**
	 * Create a new {@link BookToUser} for a book, user, and role id.
	 * @param bookId {@link Book} id
	 * @param userId {@link User} id
	 * @param roleId {@link Role} id
	 * @return Persisted {@link BookToUser}.
	 */
	BookToUser create(long bookId, long userId, long roleId);

	/**
	 * Delete a {@link BookToUser} by id.
	 * @param id {@link BookToUser}'s id.
	 */
	void deleteById(long id);

	/**
	 * Find all {@link BookToUser} for a {@link Book} id.
	 * @param id {@link Book} id.
	 * @return {@link List} of {@link BookToUser}.
	 */
	List<BookToUser> findAllByBookId(long id);

	/**
	 * Find all {@link BookToUser} for a {@link User} id.
	 * @param id {@link User} id.
	 * @return {@link List} of {@link BookToUser}.
	 */
	List<BookToUser> findAllByUserId(long id);

}
