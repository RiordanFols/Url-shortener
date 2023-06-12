package ru.chernov.urlshortener.exception.token;

import ru.chernov.urlshortener.exception.AbstractNotFoundException;


public class TokenNotFoundException extends AbstractNotFoundException {

    public TokenNotFoundException() {
        super("token");
    }

}
