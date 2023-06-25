package ru.chernov.urlshortener.enums.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.chernov.urlshortener.enums.attribute.AttributeEnum;

import java.util.Set;


@Getter
@AllArgsConstructor
public enum UserStatus implements AttributeEnum<String> {
    /**
     * Active user
     */
    ACTIVE("ACTIVE"),
    /**
     * User deleted itself
     */
    DELETED("DELETED"),
    /**
     * User was frozen for investigation
     */
    FROZEN("FROZEN"),
    /**
     * User was permanently blocked
     */
    BLOCKED("BLOCKED");

    private final String dbValue;


    public static final Set<UserStatus> USER_WORKS_STATUSES = Set.of(ACTIVE);
    public static final Set<UserStatus> TOKEN_WORKS_STATUSES = Set.of(ACTIVE, FROZEN);
}
