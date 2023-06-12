package ru.chernov.urlshortener.exception;

import org.springframework.http.HttpStatus;


public abstract class AbstractNotFoundException extends LocalizableResponseStatusException {
    private static final String NOT_FOUND = ".not-found";


    protected AbstractNotFoundException(String code) {
        super(HttpStatus.NOT_FOUND, code + NOT_FOUND);
    }

}
