package ru.chernov.urlshortener.exception.setting;

import ru.chernov.urlshortener.exception.AbstractNotFoundException;


public class SettingNotFoundException extends AbstractNotFoundException {

    public SettingNotFoundException() {
        super("setting");
    }

}
