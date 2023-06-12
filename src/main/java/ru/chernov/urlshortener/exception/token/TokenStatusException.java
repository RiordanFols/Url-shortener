package ru.chernov.urlshortener.exception.token;

import org.springframework.http.HttpStatus;
import ru.chernov.urlshortener.enums.token.TokenStatus;
import ru.chernov.urlshortener.exception.LocalizableResponseStatusException;


public class TokenStatusException extends LocalizableResponseStatusException {

    public TokenStatusException(TokenStatus status) {
        super(HttpStatus.CONFLICT, "token-status", status.name());
    }

}
