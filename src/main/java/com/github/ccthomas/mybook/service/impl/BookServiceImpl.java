package com.github.ccthomas.mybook.service.impl;

import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.models.book.ExternalLink;
import com.github.ccthomas.mybook.models.user.Role;
import com.github.ccthomas.mybook.repository.BookRepository;
import com.github.ccthomas.mybook.repository.ExternalLinkRepository;
import com.github.ccthomas.mybook.service.BookService;
import com.github.ccthomas.mybook.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private static Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ExternalLinkRepository externalLinkRepository;

    @Autowired
    private UserService userService;

    @Override
    public void deleteById(long id) {
        LOGGER.info("Deleting book with id={}", id);
        bookRepository.deleteById(id);
    }

    @Override
    public Book findById(long id) {
        LOGGER.info("Finding Book with id={}", id);
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isEmpty()) {
            LOGGER.info("Book does not exist for id=" + id);
            return null;
        }

        Book Book = optionalBook.get();
        LOGGER.info("returning Book={}", Book);
        return Book;
    }

    @Override
    public ExternalLink roleAccessAdd(long externalLinkId, long roleId) {
        LOGGER.info("Add Role Access for role with id={}, to External Link with id={}", roleId, externalLinkId);

        ExternalLink externalLink = findExternalLinkById(externalLinkId);
        if (externalLink == null) {
            throw new IllegalStateException("No ExternalLink exists for id=" + externalLinkId);
        }

        Role role = userService.findRoleById(roleId);
        if (role == null) {
            throw new IllegalStateException("No Role exists for id=" + roleId);
        }

        List<Role> roleAccess = externalLink.getRoleAccess();
        if (roleAccess == null) {
            LOGGER.info("Role Access is null");
            roleAccess = new ArrayList<>();
            externalLink.setRoleAccess(roleAccess);
        }
        LOGGER.info("Adding Role Access of role={} to external link={}", role, externalLink);
        roleAccess.add(role);
        return externalLinkRepository.save(externalLink);
    }

    @Override
    public ExternalLink roleAccessRemove(long externalLinkId, long roleId) {
        LOGGER.info("Remove Role Access for role with id={}, to External Link with id={}", roleId, externalLinkId);

        ExternalLink externalLink = findExternalLinkById(externalLinkId);
        if (externalLink == null) {
            LOGGER.info("External Access is null");
            throw new IllegalStateException("No ExternalLink exists for id=" + externalLinkId);
        }

        List<Role> roleAccess = externalLink.getRoleAccess();
        if (roleAccess == null || roleAccess.isEmpty()) {
            throw new IllegalStateException("No Role Access for External Link with id=" + externalLinkId);
        }

        LOGGER.info("Find Role to remove");
        boolean roleExistsWithId = false;
        for (Role role : roleAccess) {
            LOGGER.info("Looking at role={}", role);
            if (role.getId() == roleId) {
                LOGGER.info("Removing Role");
                roleAccess.remove(role);
                roleExistsWithId = true;
                break;
            }
        }

        if (!roleExistsWithId) {
            throw new IllegalStateException("No role removed for externalId=" + externalLinkId + " and roleId=" + roleId);
        }
        return externalLinkRepository.save(externalLink);
    }

    @Override
    public Book save(Book book) {
        LOGGER.info("Saving Book={}", book);
        return bookRepository.save(book);
    }

    // ========================
    // === Internal Methods ===
    // ========================

    ExternalLink findExternalLinkById(long externalId) {
        LOGGER.info("Finding External Link with id={}", externalId);
        Optional<ExternalLink> optionalExternalLink = externalLinkRepository.findById(externalId);
        if (optionalExternalLink.isEmpty()) {
            LOGGER.info("External Link does not exist for id=" + externalId);
            return null;
        }

        ExternalLink externalLink = optionalExternalLink.get();
        LOGGER.info("returning External Link={}", externalLink);
        return externalLink;
    }
}
