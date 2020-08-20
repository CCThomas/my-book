package com.github.ccthomas.mybook.models.user;

import com.github.ccthomas.mybook.models.book.Book;
import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entity representing a {@link User}'s Role
 *
 * <p>
 * A Role is assigned from a {@link Book} to a {@link User}. As a single {@link User} can have multiple roles, either
 * for an individual {@link Book}, or multiple, a role will not be stored on the {@link User} model, but a Book to User
 * Association.
 * </p>
 *
 * @author CCThomas
 */
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true)
    @NotNull
    private String title;

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

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
