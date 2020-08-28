package com.github.ccthomas.mybook.controllers;

import com.github.ccthomas.mybook.models.association.BookToUser;
import com.github.ccthomas.mybook.service.BookToUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Rest API endpoints for api/bookToUser
 *
 * <p>
 * This Controller acts as a bridge between the consumer/UI and the
 * {@link BookToUserService}
 * </p>
 *
 * @author CCThomas
 */
@RestController
@RequestMapping("/bookToUser")
public class BookToUserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookToUserController.class);

	@Autowired
	BookToUserService bookToUserService;

	@GetMapping("/create/{bookId}/{userId}/{roleId}")
	public BookToUser create(@PathVariable long bookId, @PathVariable long userId, @PathVariable long roleId) {
		LOGGER.info("api /bookToUser/create/{bookId}/{userId}/{roleId} hit with bookId={}, userId={}, and roleId={}",
				bookId, userId, roleId);
		return bookToUserService.create(bookId, userId, roleId);
	}

	@RequestMapping("/delete/{id}")
	public HttpStatus deleteById(@PathVariable long id) {
		LOGGER.info("api /bookToUser/delete/{id} hit with id={}", id);
		bookToUserService.deleteById(id);
		return HttpStatus.OK;
	}

	@GetMapping("/bookToUsers/bookId/{id}")
	public List<BookToUser> findAllByBookId(@PathVariable long id) {
		LOGGER.info("api /bookToUser/bookToUsers/bookId/{id} hit with id={}", id);
		return bookToUserService.findAllByBookId(id);
	}

	@GetMapping("/bookToUsers/userId/{id}")
	public List<BookToUser> findAllByUserId(@PathVariable long id) {
		LOGGER.info("api /bookToUser/bookToUsers/userId/{id} hit with id={}", id);
		return bookToUserService.findAllByUserId(id);
	}

}
