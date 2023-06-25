package ru.chernov.urlshortener.converter.attribute;

public interface EnumValuesProvider<E extends Enum<E>> {

    E[] getEnumValues();

}
