package ru.chernov.urlshortener.exception.setting;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class SettingWrongTypeException extends ResponseStatusException {

    public SettingWrongTypeException() {
        super(HttpStatus.CONFLICT);
    }

}
