package ru.chernov.urlshortener.exception.user;

import org.springframework.http.HttpStatus;
import ru.chernov.urlshortener.exception.LocalizableResponseStatusException;


public class UserWrongPasswordException extends LocalizableResponseStatusException {

    public UserWrongPasswordException() {
        super(HttpStatus.BAD_REQUEST, "user-password", "not-correct");
    }

}
