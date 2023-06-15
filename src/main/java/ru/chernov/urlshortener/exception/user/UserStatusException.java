package ru.chernov.urlshortener.exception.user;

import org.springframework.http.HttpStatus;
import ru.chernov.urlshortener.enums.user.UserStatus;
import ru.chernov.urlshortener.exception.LocalizableResponseStatusException;


public class UserStatusException extends LocalizableResponseStatusException {

    public UserStatusException(UserStatus status) {
        super(HttpStatus.CONFLICT, "user-status", status.name());
    }

}
