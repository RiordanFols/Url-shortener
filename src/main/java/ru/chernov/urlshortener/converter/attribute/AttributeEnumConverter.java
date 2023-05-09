package ru.chernov.urlshortener.converter.attribute;

import jakarta.persistence.AttributeConverter;
import org.springframework.lang.Nullable;
import ru.chernov.urlshortener.enums.attribute.AttributeEnum;

import java.util.Objects;


public interface AttributeEnumConverter<E extends Enum<E> & AttributeEnum<T>, T>
        extends AttributeConverter<E, T>, EnumValuesProvider<E> {

    @Override
    default T convertToDatabaseColumn(@Nullable E someEnum) {
        return someEnum == null ? null : someEnum.getDbValue();
    }


    @Override
    default E convertToEntityAttribute(@Nullable T dbValue) {
        if (dbValue == null) {
            return null;
        }

        for (E attribute : getEnumValues()) {
            if (Objects.equals(attribute.getDbValue(), dbValue)) {
                return attribute;
            }
        }

        return null;
    }

}
