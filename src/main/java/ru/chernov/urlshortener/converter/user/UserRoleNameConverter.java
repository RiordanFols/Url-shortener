package ru.chernov.urlshortener.converter.user;

import jakarta.persistence.Converter;
import ru.chernov.urlshortener.converter.attribute.AttributeEnumConverter;
import ru.chernov.urlshortener.enums.user.UserRoleName;


@Converter
public class UserRoleNameConverter implements AttributeEnumConverter<UserRoleName, String> {

    @Override
    public UserRoleName[] getEnumValues() {
        return UserRoleName.values();
    }

}
