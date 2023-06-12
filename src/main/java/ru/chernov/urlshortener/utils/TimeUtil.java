package ru.chernov.urlshortener.utils;

import java.time.LocalDateTime;

import static java.time.ZoneOffset.UTC;


public class TimeUtil {

    public static LocalDateTime utcNow() {
        return LocalDateTime.now(UTC);
    }

}
