package com.github.ccthomas.mybook.models.book;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity representing a Book
 *
 * @author CCThomas
 */
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<ExternalLink> externalLinks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<ExternalLink> getExternalLinks() {
		return externalLinks;
	}

	public void setExternalLinks(List<ExternalLink> externalLinks) {
		this.externalLinks = externalLinks;
	}

	@Override
	public String toString() {
		return "Book{" + "id=" + id + ", title='" + title + '\'' + ", externalLinks="
				+ (externalLinks == null ? null
						: externalLinks.stream().map(el -> el.toString()).collect(Collectors.joining(",", "[", "]")))
				+ '}';
	}

}
