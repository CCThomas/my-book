package com.github.ccthomas.mybook.service;

import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.models.book.ExternalLink;
import com.github.ccthomas.mybook.models.user.Role;
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
	 * @param id {@link Book}'s id.
	 */
	void deleteById(long id);

	/**
	 * Finds a {@link Book} by id.
	 * @param id {@link Book}'s id.
	 * @return {@link Book} for given id.
	 */
	Book findById(long id);

	/**
	 * Add {@link Role} access to an
	 * {@link com.github.ccthomas.mybook.models.book.ExternalLink}
	 * @param externalLinkId {@link com.github.ccthomas.mybook.models.book.ExternalLink}
	 * id
	 * @param roleId {@link Role} id
	 * @return {@link ExternalLink}
	 */
	ExternalLink roleAccessAdd(long externalLinkId, long roleId);

	/**
	 * Remove {@link Role} access to an
	 * {@link com.github.ccthomas.mybook.models.book.ExternalLink}
	 * @param externalLinkId {@link com.github.ccthomas.mybook.models.book.ExternalLink}
	 * id
	 * @param roleId {@link Role} id
	 * @return {@link ExternalLink}
	 */
	ExternalLink roleAccessRemove(long externalLinkId, long roleId);

	/**
	 * Saves a {@link Book}.
	 * @param book {@link Book} to persist.
	 * @return Persisted {@link Book}.
	 */
	Book save(Book book);

}
