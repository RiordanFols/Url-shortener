package ru.chernov.urlshortener.exception.setting;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class SettingNotFoundException extends ResponseStatusException {

    public SettingNotFoundException() {
        super(HttpStatus.NOT_FOUND);
    }

}
