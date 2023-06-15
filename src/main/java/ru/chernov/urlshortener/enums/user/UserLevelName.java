package ru.chernov.urlshortener.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum UserLevelName {
    NONE("NONE"),
    BRONZE("BRONZE"),
    SILVER("SILVER"),
    GOLDEN("GOLDEN");

    private final String dbValue;
}
