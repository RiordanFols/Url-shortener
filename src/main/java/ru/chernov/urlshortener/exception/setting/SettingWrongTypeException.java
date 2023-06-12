package ru.chernov.urlshortener.exception.setting;

import org.springframework.http.HttpStatus;
import ru.chernov.urlshortener.exception.LocalizableResponseStatusException;


public class SettingWrongTypeException extends LocalizableResponseStatusException {

    public SettingWrongTypeException() {
        super(HttpStatus.CONFLICT, "setting", "wrong-type");
    }

}
