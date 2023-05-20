package ru.chernov.urlshortener.exception.setting;

import org.springframework.http.HttpStatus;
import ru.chernov.urlshortener.exception.LocalizableResponseStatusException;


public class SettingNotFoundException extends LocalizableResponseStatusException {

    public SettingNotFoundException() {
        super(HttpStatus.NOT_FOUND, "setting.not-found");
    }

}
