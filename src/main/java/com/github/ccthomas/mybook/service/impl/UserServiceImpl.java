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

@Service
public class UserServiceImpl implements UserService {
    private static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

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
    public Role findRoleById(long id) {
        LOGGER.info("Finding role with id={}", id);
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isEmpty()) {
            LOGGER.info("Role does not exist for id=" + id);
            return null;
        }

        Role role = optionalRole.get();
        LOGGER.info("returning role={}", role);
        return role;
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
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        LOGGER.info("Saving role={}", role);
        return roleRepository.save(role);
    }
}
