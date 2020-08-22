package com.github.ccthomas.mybook.models.association;

import com.github.ccthomas.mybook.models.book.Book;
import com.github.ccthomas.mybook.models.user.Role;
import com.github.ccthomas.mybook.models.user.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
public class BookToUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private Book book;

	@OneToOne
	private User user;

	@OneToOne
	private Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "BookToUser{" + "id=" + id + ", book=" + book + ", user=" + user + ", role=" + role + '}';
	}

}
