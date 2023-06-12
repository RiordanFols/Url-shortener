package ru.chernov.urlshortener.exception.user;

import ru.chernov.urlshortener.exception.AbstractNotFoundException;


public class UserNotFoundException extends AbstractNotFoundException {

    public UserNotFoundException() {
        super("user");
    }

}
