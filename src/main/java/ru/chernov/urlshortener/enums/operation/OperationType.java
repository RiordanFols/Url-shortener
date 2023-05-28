package ru.chernov.urlshortener.enums.operation;

import lombok.Getter;
import ru.chernov.urlshortener.enums.attribute.AttributeEnum;


@Getter
public enum OperationType implements AttributeEnum<String> {
    SHORTEN("SHORTEN"),
    REDIRECT("REDIRECT");

    private final String dbValue;


    OperationType(String dbValue) {
        this.dbValue = dbValue;
    }

}
