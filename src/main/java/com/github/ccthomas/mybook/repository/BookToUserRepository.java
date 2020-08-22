package com.github.ccthomas.mybook.repository;

import com.github.ccthomas.mybook.models.association.BookToUser;
import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for {@link BookToUser}
 *
 * @author CCThomas
 */
@Repository
public interface BookToUserRepository extends JpaRepository<BookToUser, Long> {

	/**
	 * Find all {@link BookToUser} by {@link Book}'s id
	 * @param id {@link Book}'s id
	 * @return {@link List} of {@link BookToUser}
	 */
	List<BookToUser> findAllByBookId(long id);

	/**
	 * Find all {@link BookToUser} by {@link User}'s id
	 * @param id {@link User}'s id
	 * @return {@link List} of {@link BookToUser}
	 */
	List<BookToUser> findAllByUserId(long id);

	boolean existsByBookIdAndUserIdAndRoleId(long bookId, long userId, long roleId);

}
