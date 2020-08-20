package com.github.ccthomas.mybook.service.impl;

import com.github.ccthomas.mybook.models.user.Role;
import com.github.ccthomas.mybook.models.user.User;
import com.github.ccthomas.mybook.repository.RoleRepository;
import com.github.ccthomas.mybook.repository.UserRepository;
import com.github.ccthomas.mybook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This is an example of having two different implementations for a common service. The {@link User} Entity uses javax
 * persistence annotations like @NotNull and @Column(unique=true). If the Database is initialized by JPA, those
 * annotations serve a purpose. If the Database already exists, then those annotations are purely show. For those cases,
 * this implementation could be use to check those validations on the {@link User} entity.
 *
 * <p>
 * This implementation shares a lot of code with UserServiceImpl. Would be good to log a Spike, or investigation issue
 * to look at pulling that code into an abstract class.
 * </p>
 *
 * @author CCThomas
 */
@Service
public class UserServiceExistingDB implements UserService {
    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceExistingDB.class);

    /**
     * This number will be incremented before use.
     */
    public static final long STARTING_ID = 0l;
    public static Long previousGeneratedId;
    public static Long previousRoleGeneratedId;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void deleteById(long id) {
        LOGGER.info("Deleting user with id={}", id);
        userRepository.deleteById(id);
    }

    @Override
    public void deleteRoleById(long id) {
        LOGGER.info("Deleting role with id={}", id);
        roleRepository.deleteById(id);
    }

    @Override
    public List<Role> findRoleAll() {
        LOGGER.info("Find all roles");
        return roleRepository.findAll();
    }

    @Override
    public User findById(long id) {
        LOGGER.info("Finding user with id={}", id);
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            LOGGER.info("User does not exist for id=" + id);
            return null;
        }

        User user = optionalUser.get();
        LOGGER.info("returning user={}", user);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        LOGGER.info("Finding user with username={}", username);
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            LOGGER.info("User does not exist for username=" + username);
            return null;
        }

        User user = optionalUser.get();
        LOGGER.info("returning user={}", user);
        return user;
    }

    @Override
    public User save(User user) {
        LOGGER.info("Saving user={}", user);

        if (user == null) {
            throw new IllegalStateException("User is null");
        } else if (user.getId() != null) {
            LOGGER.info("Updating existing User");
            return userRepository.save(user);
        } else if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new IllegalStateException("Username is null or blank. username=" + user.getUsername());
        } else if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }

        if (previousGeneratedId == null) {
            LOGGER.info("Previously Generated Id is null");
            Optional<User> optionalExistingUser = userRepository.findFirstByOrderByIdDesc();
            previousGeneratedId = optionalExistingUser.isPresent() ? optionalExistingUser.get().getId() : STARTING_ID;
        }

        LOGGER.info("Setting Generated Id");
        previousGeneratedId++;
        user.setId(previousGeneratedId);

        LOGGER.info("Saving user={}", user);
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        LOGGER.info("Saving role={}", role);

        if (role == null) {
            throw new IllegalStateException("User is null");
        } else if (role.getId() != null) {
            LOGGER.info("Updating existing User");
            return roleRepository.save(role);
        } else if (role.getTitle() == null || role.getTitle().isBlank()) {
            throw new IllegalStateException("Title is null or blank. username=" + role.getTitle());
        } else if (roleRepository.findByTitle(role.getTitle()).isPresent()) {
            throw new IllegalStateException("Username already exists");
        }

        if (previousRoleGeneratedId == null) {
            LOGGER.info("Previously Generated Id is null");
            Optional<Role> optionalExistingRole = roleRepository.findFirstByOrderByIdDesc();
            previousGeneratedId = optionalExistingRole.isPresent() ? optionalExistingRole.get().getId() : STARTING_ID;
        }

        LOGGER.info("Setting Generated Id");
        previousGeneratedId++;
        role.setId(previousGeneratedId);

        LOGGER.info("Saving role={}", role);
        return roleRepository.save(role);
    }
}
