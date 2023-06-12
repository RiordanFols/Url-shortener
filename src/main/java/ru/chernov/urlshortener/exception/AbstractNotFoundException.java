package ru.chernov.urlshortener.exception;

import org.springframework.http.HttpStatus;


public abstract class AbstractNotFoundException extends LocalizableResponseStatusException {
    private static final String NOT_FOUND_CODE = "not-found";


    protected AbstractNotFoundException(String prefix) {
        super(HttpStatus.NOT_FOUND, prefix, NOT_FOUND_CODE);
    }

}
