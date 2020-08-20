package com.github.ccthomas.mybook.service;

import com.github.ccthomas.mybook.models.user.User;
import org.springframework.stereotype.Service;

/**
 * CRUD Service for managing {@link User}s
 *
 * @author CCThomas
 */
@Service
public interface UserService {

    /**
     * Delete a {@link User} by id.
     *
     * @param id {@link User}'s id.
     */
    void deleteById(long id);

    /**
     * Finds a {@link User} by id.
     *
     * @param id {@link User}'s id.
     * @return {@link User} for given id.
     */
    User findById(long id);

    /**
     * Finds a {@link User} by username.
     *
     * @param username {@link User}'s username
     * @return {@link User} for given username.
     */
    User findByUsername(String username);

    /**
     * Saves a {@link User}.
     *
     * @param user {@link User} to persist.
     * @return Persisted {@link User}.
     */
    User save(User user);
}
