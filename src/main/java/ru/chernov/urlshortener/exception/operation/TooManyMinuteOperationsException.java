package ru.chernov.urlshortener.exception.operation;

import ru.chernov.urlshortener.exception.LocalizableResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;


public class TooManyMinuteOperationsException extends LocalizableResponseStatusException {

    public TooManyMinuteOperationsException() {
        super(CONFLICT, "operation", "too-many-per-minute");
    }

}
