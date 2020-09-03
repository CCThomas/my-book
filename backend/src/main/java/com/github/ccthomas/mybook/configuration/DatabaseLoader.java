package com.github.ccthomas.mybook.configuration;

import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.models.book.ExternalLink;
import com.github.ccthomas.mybook.models.user.Role;
import com.github.ccthomas.mybook.models.user.User;
import com.github.ccthomas.mybook.repository.BookRepository;
import com.github.ccthomas.mybook.repository.BookToUserRepository;
import com.github.ccthomas.mybook.repository.ExternalLinkRepository;
import com.github.ccthomas.mybook.repository.RoleRepository;
import com.github.ccthomas.mybook.repository.UserRepository;
import com.github.ccthomas.mybook.service.BookService;
import com.github.ccthomas.mybook.service.BookToUserService;
import com.github.ccthomas.mybook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Loads the Database with data
 * <p>
 * Primarily used for setting up testing data, or init data configurations needed for a
 * client.
 * </p>
 *
 * @author CCThomas
 */
@Component
public class DatabaseLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseLoader.class);

	public static final String ARG_DATABASE_LOADER_PUBLISHER = "publisher";

	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookService bookService;

	@Autowired
	BookToUserRepository bookToUserRepository;

	@Autowired
	BookToUserService bookToUserService;

	@Autowired
	ExternalLinkRepository externalLinkRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	/**
	 * Load the Database with data.
	 * @param arg {@link String} specify what data to be saved.
	 */
	public void load(String arg) {
		LOGGER.info("Load from arg={}", arg);
		if (arg.contains(ARG_DATABASE_LOADER_PUBLISHER)) {
			LOGGER.info("Load Publisher...");
			loadPublisher();
		}
	}

	/**
	 * Load the Database with basic Publisher, Editor, Author, and Book data.
	 */
	void loadPublisher() {
		LOGGER.info("Clearing Database...");
		bookToUserRepository.deleteAll();
		bookRepository.deleteAll();
		externalLinkRepository.deleteAll(); // taking care of Orphans
		roleRepository.deleteAll();
		userRepository.deleteAll();

		LOGGER.info("Loading Users...");
		User author1 = new User();
		author1.setUsername("TheAmazingAuthor1");
		author1 = userService.save(author1);

		User author2 = new User();
		author2.setUsername("TheAstonishingAuthor2");
		author2 = userService.save(author2);

		User editor = new User();
		editor.setUsername("TheExtraordinaryEditor");
		editor = userService.save(editor);

		User publisher = new User();
		publisher.setUsername("ThePerfectPublisher");
		publisher = userService.save(publisher);

		LOGGER.info("Loading Roles...");
		Role authorRole = new Role();
		authorRole.setTitle("Author");
		authorRole = userService.saveRole(authorRole);

		Role editorRole = new Role();
		editorRole.setTitle("Editor");
		editorRole = userService.saveRole(editorRole);

		Role publisherRole = new Role();
		publisherRole.setTitle("Publisher");
		publisherRole = userService.saveRole(publisherRole);

		LOGGER.info("Loading Books...");
		Book theAmazingBook = new Book();
		theAmazingBook.setTitle("The Amazing Book");
		theAmazingBook = bookService.save(theAmazingBook);

		Book theAmazingBook2 = new Book();
		theAmazingBook2.setTitle("The Amazing Book 2");
		theAmazingBook2 = bookService.save(theAmazingBook2);

		Book theAstonishingBook = new Book();
		theAstonishingBook.setTitle("The Astonishing Book");
		theAstonishingBook = bookService.save(theAstonishingBook);

		LOGGER.info("Loading External Links...");
		ExternalLink externalLink1 = new ExternalLink();
		externalLink1.setLink("www.docs.com/theAmazingBookDraft");
		externalLink1.setName("Rough Draft");
		ExternalLink externalLink2 = new ExternalLink();
		externalLink2.setLink("www.docs.com/theAmazingBookBible");
		externalLink2.setName("The Amazing Author's Bible");
		ExternalLink externalLink3 = new ExternalLink();
		externalLink3.setLink("www.docs.com/theAmazingBookFinal");
		externalLink3.setName("Final Copy");
		theAmazingBook.setExternalLinks(List.of(externalLink1, externalLink2, externalLink3));
		theAmazingBook = bookService.save(theAmazingBook);
		externalLink1 = theAmazingBook.getExternalLinks().get(0);
		externalLink2 = theAmazingBook.getExternalLinks().get(1);
		externalLink3 = theAmazingBook.getExternalLinks().get(2);

		ExternalLink externalLink4 = new ExternalLink();
		externalLink4.setLink("www.docs.com/theAmazingBook2Draft");
		externalLink4.setName("Rough Draft");
		ExternalLink externalLink5 = new ExternalLink();
		externalLink5.setLink("www.docs.com/theAmazingBook2Bible");
		externalLink5.setName("The Amazing Author's Bible");
		ExternalLink externalLink6 = new ExternalLink();
		externalLink6.setLink("www.docs.com/theAmazingBookFinal");
		externalLink6.setName("The Amazing Book's Final Copy");
		theAmazingBook2.setExternalLinks(List.of(externalLink4, externalLink5, externalLink6));
		theAmazingBook2 = bookService.save(theAmazingBook2);
		externalLink4 = theAmazingBook2.getExternalLinks().get(0);
		externalLink5 = theAmazingBook2.getExternalLinks().get(1);
		externalLink6 = theAmazingBook2.getExternalLinks().get(2);

		ExternalLink externalLink7 = new ExternalLink();
		externalLink7.setLink("www.docs.com/theAstonishingBookDraft");
		externalLink7.setName("Rough Draft");
		ExternalLink externalLink8 = new ExternalLink();
		externalLink8.setLink("www.docs.com/theAstonishingBookBible");
		externalLink8.setName("The Astonishing Author's Bible");
		theAstonishingBook.setExternalLinks(List.of(externalLink7, externalLink8));
		theAstonishingBook = bookService.save(theAstonishingBook);
		externalLink7 = theAstonishingBook.getExternalLinks().get(0);
		externalLink8 = theAstonishingBook.getExternalLinks().get(1);

		LOGGER.info("Loading Role Access to Links...");
		LOGGER.info("{} and {}", externalLink1, authorRole);
		bookService.roleAccessAdd(externalLink1.getId(), authorRole.getId());
		bookService.roleAccessAdd(externalLink1.getId(), editorRole.getId());
		bookService.roleAccessAdd(externalLink1.getId(), publisherRole.getId());
		bookService.roleAccessAdd(externalLink2.getId(), authorRole.getId());
		bookService.roleAccessAdd(externalLink2.getId(), publisherRole.getId());
		bookService.roleAccessAdd(externalLink3.getId(), authorRole.getId());
		bookService.roleAccessAdd(externalLink3.getId(), editorRole.getId());
		bookService.roleAccessAdd(externalLink3.getId(), publisherRole.getId());

		bookService.roleAccessAdd(externalLink4.getId(), authorRole.getId());
		bookService.roleAccessAdd(externalLink4.getId(), editorRole.getId());
		bookService.roleAccessAdd(externalLink4.getId(), publisherRole.getId());
		bookService.roleAccessAdd(externalLink5.getId(), authorRole.getId());
		bookService.roleAccessAdd(externalLink5.getId(), publisherRole.getId());
		bookService.roleAccessAdd(externalLink6.getId(), authorRole.getId());
		bookService.roleAccessAdd(externalLink6.getId(), editorRole.getId());
		bookService.roleAccessAdd(externalLink6.getId(), publisherRole.getId());

		bookService.roleAccessAdd(externalLink7.getId(), authorRole.getId());
		bookService.roleAccessAdd(externalLink7.getId(), editorRole.getId());
		bookService.roleAccessAdd(externalLink7.getId(), publisherRole.getId());
		bookService.roleAccessAdd(externalLink8.getId(), authorRole.getId());
		bookService.roleAccessAdd(externalLink8.getId(), publisherRole.getId());

		LOGGER.info("Loading Book to Users...");
		bookToUserService.create(theAmazingBook.getId(), author1.getId(), authorRole.getId());
		bookToUserService.create(theAmazingBook.getId(), publisher.getId(), editorRole.getId());
		bookToUserService.create(theAmazingBook.getId(), publisher.getId(), publisherRole.getId());

		bookToUserService.create(theAmazingBook2.getId(), author1.getId(), authorRole.getId());
		bookToUserService.create(theAmazingBook2.getId(), editor.getId(), editorRole.getId());
		bookToUserService.create(theAmazingBook2.getId(), publisher.getId(), publisherRole.getId());

		bookToUserService.create(theAstonishingBook.getId(), author2.getId(), authorRole.getId());
		bookToUserService.create(theAstonishingBook.getId(), publisher.getId(), publisherRole.getId());
	}

}
