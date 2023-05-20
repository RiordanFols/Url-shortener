package ru.chernov.urlshortener.converter.attribute;

public interface EnumValuesProvider<E extends Enum<E>> {

    /**
     * Как-то вызвать статический метод values() на классе самого енама не получится,
     * так как этот метод генерится компилятором.
     */
    E[] getEnumValues();

}
