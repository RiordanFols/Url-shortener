package ru.chernov.urlshortener.enums.setting;

import ru.chernov.urlshortener.enums.attribute.AttributeEnum;


public enum SettingType implements AttributeEnum<String> {
    DURATION("DURATION");

    private final String dbValue;


    SettingType(String dbValue) {
        this.dbValue = dbValue;
    }


    @Override
    public String getDbValue() {
        return dbValue;
    }
}
