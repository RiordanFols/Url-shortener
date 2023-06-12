package ru.chernov.urlshortener.exception.link;

import ru.chernov.urlshortener.exception.AbstractNotFoundException;


public class LinkNotFoundException extends AbstractNotFoundException {

    public LinkNotFoundException() {
        super("link");
    }

}
