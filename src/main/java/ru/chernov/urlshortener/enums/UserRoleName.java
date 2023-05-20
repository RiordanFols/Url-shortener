package ru.chernov.urlshortener.enums;

import ru.chernov.urlshortener.enums.attribute.AttributeEnum;


public enum UserRoleName implements AttributeEnum<String> {
    BASIC("BASIC");

    private final String dbValue;


    UserRoleName(String dbValue) {
        this.dbValue = dbValue;
    }


    @Override
    public String getDbValue() {
        return dbValue;
    }

}
