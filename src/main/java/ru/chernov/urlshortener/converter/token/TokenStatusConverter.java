package ru.chernov.urlshortener.converter.token;

import jakarta.persistence.Converter;
import ru.chernov.urlshortener.converter.attribute.AttributeEnumConverter;
import ru.chernov.urlshortener.enums.token.TokenStatus;


@Converter
public class TokenStatusConverter implements AttributeEnumConverter<TokenStatus, String> {

    @Override
    public TokenStatus[] getEnumValues() {
        return TokenStatus.values();
    }

}
