package ru.chernov.urlshortener.exception.operation;

import ru.chernov.urlshortener.exception.LocalizableResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;


public class TooManyMonthOperationsException extends LocalizableResponseStatusException {

    public TooManyMonthOperationsException() {
        super(CONFLICT, "operation", "too-many-per-month");
    }

}
