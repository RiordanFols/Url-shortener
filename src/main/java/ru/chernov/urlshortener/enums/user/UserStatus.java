package ru.chernov.urlshortener.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.chernov.urlshortener.enums.attribute.AttributeEnum;

import java.util.Set;


@Getter
@AllArgsConstructor
public enum UserStatus implements AttributeEnum<String> {
    /**
     * Активный пользователь
     */
    ACTIVE("ACTIVE"),
    /**
     * Пользователь сам удалил себя из системы
     */
    DELETED("DELETED"),
    /**
     * Пользователь временно заблокирован для каких-то разбирательств
     */
    FROZEN("FROZEN"),
    /**
     * Пользователь был перманентно заблокирован
     */
    BLOCKED("BLOCKED");

    private final String dbValue;


    public static final Set<UserStatus> USER_WORKS_STATUSES = Set.of(ACTIVE);
    public static final Set<UserStatus> TOKEN_WORKS_STATUSES = Set.of(ACTIVE, FROZEN);
}
