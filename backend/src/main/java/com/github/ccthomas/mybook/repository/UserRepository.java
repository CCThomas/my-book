package com.github.ccthomas.mybook.repository;

import com.github.ccthomas.mybook.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JPA Repository for {@link User}
 *
 * @author CCThomas
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Finds all {@link User}s by email and provider.
	 * @param email {@link String} {@link User}'s email.
	 * @param provider {@link String} {@link User}'s provider.
	 * @return {@link List} of {@link User}.
	 */
	List<User> findAllByEmailAndProvider(String email, String provider);

}
