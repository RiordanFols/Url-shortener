package ru.chernov.urlshortener.converter;

import jakarta.persistence.Converter;
import ru.chernov.urlshortener.converter.attribute.AttributeEnumConverter;
import ru.chernov.urlshortener.enums.setting.SettingType;


@Converter
public class SettingTypeConverter implements AttributeEnumConverter<SettingType, String> {

    @Override
    public SettingType[] getEnumValues() {
        return SettingType.values();
    }

}
