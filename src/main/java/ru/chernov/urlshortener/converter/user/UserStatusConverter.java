package ru.chernov.urlshortener.converter.user;

import jakarta.persistence.Converter;
import ru.chernov.urlshortener.converter.attribute.AttributeEnumConverter;
import ru.chernov.urlshortener.enums.user.UserStatus;


@Converter
public class UserStatusConverter implements AttributeEnumConverter<UserStatus, String> {

    @Override
    public UserStatus[] getEnumValues() {
        return UserStatus.values();
    }

}
