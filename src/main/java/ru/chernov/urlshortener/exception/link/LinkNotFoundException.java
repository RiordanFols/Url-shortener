package ru.chernov.urlshortener.exception.link;

import org.springframework.http.HttpStatus;
import ru.chernov.urlshortener.exception.LocalizableResponseStatusException;


public class LinkNotFoundException extends LocalizableResponseStatusException {

    public LinkNotFoundException() {
        super(HttpStatus.NOT_FOUND, "link.not-found");
    }

}
