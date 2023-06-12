package ru.chernov.urlshortener.enums.token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.chernov.urlshortener.enums.attribute.AttributeEnum;


@Getter
@AllArgsConstructor
public enum TokenStatus implements AttributeEnum<String> {
    /**
     * Активный токен
     */
    ACTIVE("ACTIVE"),
    /**
     * Пользователь сам удалил токен
     */
    DELETED("DELETED"),
    /**
     * Токен был временно заморожен для разбирательств
     */
    FROZEN("FROZEN"),
    /**
     * Токен был перманентно заблокирован
     */
    BLOCKED("BLOCKED");

    private final String dbValue;
}
