package com.github.ccthomas.mybook.controllers;

import com.github.ccthomas.mybook.models.user.User;
import com.github.ccthomas.mybook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest API endpoints for api/user
 *
 * <p>
 * This Controller acts as a bridge between the consumer/UI and the {@link UserService}
 * </p>
 *
 * @author CCThomas
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/delete/{id}")
    public HttpStatus deleteById(@PathVariable long id) {
        LOGGER.info("api /user/delete/{id} hit with id={}", id);
        userService.deleteById(id);
        return HttpStatus.OK;
    }

    @GetMapping("/find/{id}")
    public User findById(@PathVariable long id) {
        LOGGER.info("api /user/find/{id} hit with id={}", id);
        return userService.findById(id);
    }

    @GetMapping("/findByUsername/{username}")
    public User findByUsername(@PathVariable String username) {
        LOGGER.info("api /user/findByUsername/{username} hit with username={}", username);
        return userService.findByUsername(username);
    }

    @GetMapping("/save")
    public User save(@RequestBody User user) {
        LOGGER.info("api /user/save hit with user={}", user);
        return userService.save(user);
    }
}
