package ru.chernov.urlshortener.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;


@Getter
public class LocalizableResponseStatusException extends ResponseStatusException {
    private final String prefix;
    private final String code;


    public LocalizableResponseStatusException(HttpStatusCode status, String prefix, String code) {
        super(status);
        this.prefix = prefix;
        this.code = code;
    }

}
