package ru.chernov.urlshortener.enums.operation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.chernov.urlshortener.enums.attribute.AttributeEnum;


@Getter
@AllArgsConstructor
public enum OperationType implements AttributeEnum<String> {
    SHORTEN("SHORTEN"),
    REDIRECT("REDIRECT");

    private final String dbValue;

}
