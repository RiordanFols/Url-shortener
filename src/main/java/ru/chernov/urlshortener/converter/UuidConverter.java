package ru.chernov.urlshortener.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;


@Converter(autoApply = true)
public class UuidConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        return uuid.toString();
    }


    @Override
    public UUID convertToEntityAttribute(String value) {
        return UUID.fromString(value);
    }

}
