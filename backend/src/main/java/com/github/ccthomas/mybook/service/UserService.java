package com.github.ccthomas.mybook.service;

import com.github.ccthomas.mybook.models.user.Role;
import com.github.ccthomas.mybook.models.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CRUD Service for managing {@link User}s
 *
 * @author CCThomas
 */
@Service
public interface UserService {

	/**
	 * Delete a {@link User} by id.
	 * @param id {@link User}'s id.
	 */
	void deleteById(long id);

	/**
	 * Delete a {@link Role} by id.
	 * @param id {@link Role}'s id.
	 */
	void deleteRoleById(long id);

	/**
	 * Find all {@link Role}s
	 * @return {@link List} of {@link Role}s
	 */
	List<Role> findRoleAll();

	/**
	 * Finds a {@link User} by id.
	 * @param id {@link User}'s id.
	 * @return {@link User} for given id.
	 */
	User findById(long id);

	/**
	 * Finds a {@link Role} by id.
	 * @param id {@link Role}'s id.
	 * @return {@link Role} for given id.
	 */
	Role findRoleById(long id);

	/**
	 * Finds a {@link User}s by email and provider.
	 * @param email {@link String} {@link User}'s email.
	 * @param provider {@link String} {@link User}'s provider.
	 * @return {@link User}.
	 */
	User findByEmailAndProvider(String email, String provider);

	/**
	 * Saves a {@link Role}.
	 * @param role {@link Role} to persist.
	 * @return Persisted {@link Role}.
	 */
	Role saveRole(Role role);

}
