package ru.chernov.urlshortener.exception.user;

import ru.chernov.urlshortener.exception.AbstractNotFoundException;


public class UserLevelNotFoundException extends AbstractNotFoundException {

    public UserLevelNotFoundException() {
        super("user-level");
    }

}
