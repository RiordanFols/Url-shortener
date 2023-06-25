package ru.chernov.urlshortener.enums.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.chernov.urlshortener.enums.attribute.AttributeEnum;


@Getter
@AllArgsConstructor
public enum TokenStatus implements AttributeEnum<String> {
    /**
     * Active token
     */
    ACTIVE("ACTIVE"),
    /**
     * User deleted token
     */
    DELETED("DELETED"),
    /**
     * Token was frozen for investigation
     */
    FROZEN("FROZEN"),
    /**
     * Token was permanently blocked
     */
    BLOCKED("BLOCKED");

    private final String dbValue;
}
