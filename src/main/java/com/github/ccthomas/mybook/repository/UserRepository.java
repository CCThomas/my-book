package com.github.ccthomas.mybook.repository;

import com.github.ccthomas.mybook.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * JPA Repository for {@link User}
 *
 * @author CCThomas
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find {@link User} by username.
     *
     * @return {@link Optional} {@link User}.
     */
    Optional<User> findByUsername(String username);

    /**
     * Find {@link User} with the highest id value.
     *
     * @return {@link Optional} {@link User}.
     */
    Optional<User> findFirstByOrderByIdDesc();
}
