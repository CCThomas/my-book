package com.github.ccthomas.mybook.controllers;

import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.models.book.ExternalLink;
import com.github.ccthomas.mybook.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest API endpoints for api/book
 *
 * <p>
 * This Controller acts as a bridge between the consumer/UI and the {@link BookService}
 * </p>
 *
 * @author CCThomas
 */
@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);

	@Autowired
	BookService bookService;

	@RequestMapping("/delete/{id}")
	public HttpStatus deleteById(@PathVariable long id) {
		LOGGER.info("api /book/delete/{id} hit with id={}", id);
		bookService.deleteById(id);
		return HttpStatus.OK;
	}

	@GetMapping("/{id}")
	public Book findById(@PathVariable long id) {
		LOGGER.info("api /book/find/{id} hit with id={}", id);
		return bookService.findById(id);
	}

	@GetMapping("/roleAccess/add/{externalAccessId}/{roleId}")
	public ExternalLink roleAccessAdd(@PathVariable long externalAccessId, @PathVariable long roleId) {
		LOGGER.info("api /book/roleAccess/add/{externalAccessId}/{roleId} hit with externalAccessId={} and roleId={}",
				externalAccessId, roleId);
		return bookService.roleAccessAdd(externalAccessId, roleId);
	}

	@GetMapping("/roleAccess/remove/{externalAccessId}/{roleId}")
	public ExternalLink roleAccessRemove(@PathVariable long externalAccessId, @PathVariable long roleId) {
		LOGGER.info(
				"api /book/roleAccess/remove/{externalAccessId}/{roleId} hit with externalAccessId={} and roleId={}",
				externalAccessId, roleId);
		return bookService.roleAccessRemove(externalAccessId, roleId);
	}

	@GetMapping("/save")
	public Book save(@RequestBody Book book) {
		LOGGER.info("api /Book/save hit with book={}", book);
		return bookService.save(book);
	}

}
