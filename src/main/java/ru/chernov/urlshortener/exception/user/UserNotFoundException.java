package ru.chernov.urlshortener.exception.user;

import org.springframework.http.HttpStatus;
import ru.chernov.urlshortener.exception.LocalizableResponseStatusException;


public class UserNotFoundException extends LocalizableResponseStatusException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "user.not-found");
    }

}
