package ru.chernov.urlshortener.exception.jwt;

import org.springframework.http.HttpStatus;
import ru.chernov.urlshortener.exception.LocalizableResponseStatusException;


public class JwtExpiredException extends LocalizableResponseStatusException {

    public JwtExpiredException() {
        super(HttpStatus.UNAUTHORIZED, "jwt", "expired");
    }

}
