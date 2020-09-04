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
public class UserServiceAuth0 implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceAuth0.class);

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
	public User findByEmailAndProvider(String email, String provider) {
		LOGGER.info("Finding user with email={} and provider={}", email, provider);
		if (email == null || email.isBlank() || provider == null || provider.isBlank()) {
			throw new IllegalStateException(
					"Email or Provider is null, empty, or blank. email=" + email + " and provider=" + provider);
		}

		List<User> users = userRepository.findAllByEmailAndProvider(email, provider);
		User user;
		if (users.size() == 1) {
			user = users.get(0);
		}
		else if (users.isEmpty()) {
			LOGGER.info("No User exists for email={} amd provider={}", email, provider);
			user = new User();
			user.setEmail(email);
			user.setProvider(provider);
			LOGGER.info("Creating new user={}", user);
			user = userRepository.save(user);
		}
		else {
			throw new IllegalStateException("Multiple Users exist for email=" + email + " and provider=" + provider);
		}
		LOGGER.info("returning user={}", user);
		return user;
	}

	@Override
	public Role saveRole(Role role) {
		LOGGER.info("Saving role={}", role);
		return roleRepository.save(role);
	}

}
