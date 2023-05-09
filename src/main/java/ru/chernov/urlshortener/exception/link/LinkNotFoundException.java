package ru.chernov.urlshortener.exception.link;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


// TODO: коды ошибок с сообщениями на англ
public class LinkNotFoundException extends ResponseStatusException {

    public LinkNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

}
