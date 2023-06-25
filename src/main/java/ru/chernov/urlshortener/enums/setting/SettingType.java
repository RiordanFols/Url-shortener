package ru.chernov.urlshortener.enums.setting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.chernov.urlshortener.enums.attribute.AttributeEnum;


@Getter
@AllArgsConstructor
public enum SettingType implements AttributeEnum<String> {
    DURATION("DURATION"),
    STRING("STRING");

    private final String dbValue;

}
