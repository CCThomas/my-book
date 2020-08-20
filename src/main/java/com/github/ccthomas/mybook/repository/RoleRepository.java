package com.github.ccthomas.mybook.repository;

import com.github.ccthomas.mybook.models.user.Role;
import com.github.ccthomas.mybook.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JPA Repository for {@link Role}
 *
 * @author CCThomas
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * Find {@link Role} by title.
     *
     * @return {@link Optional} {@link Role}.
     */
    Optional<User> findByTitle(String title);

    /**
     * Find {@link Role} with the highest id value.
     *
     * @return {@link Optional} {@link Role}.
     */
    Optional<Role> findFirstByOrderByIdDesc();
}
