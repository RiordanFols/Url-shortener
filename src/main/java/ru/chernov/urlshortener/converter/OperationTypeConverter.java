package ru.chernov.urlshortener.converter;

import jakarta.persistence.Converter;
import ru.chernov.urlshortener.converter.attribute.AttributeEnumConverter;
import ru.chernov.urlshortener.enums.operation.OperationType;


@Converter
public class OperationTypeConverter implements AttributeEnumConverter<OperationType, String> {

    @Override
    public OperationType[] getEnumValues() {
        return OperationType.values();
    }

}
