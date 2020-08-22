package com.github.ccthomas.mybook.models.book;

import com.github.ccthomas.mybook.models.user.Role;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ExternalLinkTest {

    @Mock
    Role role;

    private final Long id = 1l;
    private final String name = "name";
    private final String link = "link";

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getSetId() {
        // setup
        ExternalLink externalLink = new ExternalLink();

        // exercise
        externalLink.setId(id);

        // verify
        assertEquals(id, externalLink.getId());
    }

    @Test
    public void getSetName() {
        // setup
        ExternalLink externalLink = new ExternalLink();

        // exercise
        externalLink.setName(name);

        // verify
        assertEquals(name, externalLink.getName());
    }

    @Test
    public void getSetLink() {
        // setup
        ExternalLink externalLink = new ExternalLink();

        // exercise
        externalLink.setLink(link);

        // verify
        assertEquals(link, externalLink.getLink());
    }

    @Test
    public void getSetRoleAccess() {
        // setup
        ExternalLink externalLink = new ExternalLink();

        // exercise
        externalLink.setRoleAccess(List.of(role));

        // verify
        assertEquals(List.of(role), externalLink.getRoleAccess());
    }

    @Test
    public void toString_empty() {
        // setup
        String expected = "ExternalLink{id=null, name='null', link='null', roleAccess='null'}";
        ExternalLink externalLink = new ExternalLink();

        // exercise & verify
        assertEquals(expected, externalLink.toString());
    }

    @Test
    public void toString_populated() {
        // setup
        String expected = "ExternalLink{id=1, name='name', link='link', roleAccess='role'}";
        ExternalLink externalLink = new ExternalLink();
        externalLink.setId(id);
        externalLink.setName(name);
        externalLink.setLink(link);
        externalLink.setRoleAccess(List.of(role));
        when(role.toString()).thenReturn("role");

        // exercise & verify
        assertEquals(expected, externalLink.toString());
    }
}
