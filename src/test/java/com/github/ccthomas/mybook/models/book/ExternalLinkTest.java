package com.github.ccthomas.mybook.models.book;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExternalLinkTest {

    private final Long id = 1l;
    private final String name = "name";
    private final String link = "link";

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
    public void toString_empty() {
        // setup
        String expected = "ExternalLink{id=null, name='null', link='null'}";
        ExternalLink externalLink = new ExternalLink();

        // exercise & verify
        assertEquals(expected, externalLink.toString());
    }

    @Test
    public void toString_populated() {
        // setup
        String expected = "ExternalLink{id=1, name='name', link='link'}";
        ExternalLink externalLink = new ExternalLink();
        externalLink.setId(id);
        externalLink.setName(name);
        externalLink.setLink(link);

        // exercise & verify
        assertEquals(expected, externalLink.toString());
    }
}
