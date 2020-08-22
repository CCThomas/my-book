package com.github.ccthomas.mybook.models.book;

import com.github.ccthomas.mybook.models.user.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Entity representing a {@link Book}'s External Links
 *
 * @author CCThomas
 */
@Entity
public class ExternalLink {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String link;

	@OneToMany
	private List<Role> roleAccess;

	public ExternalLink() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public List<Role> getRoleAccess() {
		return roleAccess;
	}

	public void setRoleAccess(List<Role> roleAccess) {
		this.roleAccess = roleAccess;
	}

	@Override
	public String toString() {
		return "ExternalLink{" + "id=" + id + ", name='" + name + '\'' + ", link='" + link + '\'' + ", roleAccess='"
				+ (roleAccess == null ? null
						: roleAccess.stream().map(r -> r.toString()).collect(Collectors.joining(",")))
				+ '\'' + '}';
	}

}
