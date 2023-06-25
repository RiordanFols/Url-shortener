package ru.chernov.urlshortener.enums.user;

import org.springframework.security.core.GrantedAuthority;


public enum UserRole implements GrantedAuthority {
    BASIC;


    @Override
    public String getAuthority() {
        return name();
    }
}
