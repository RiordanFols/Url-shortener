package ru.chernov.urlshortener.dto.setting;

import ru.chernov.urlshortener.entity.Setting;

import java.time.Duration;
import java.util.function.Function;


public record SettingKey<T>(String name, Function<Setting, T> parser) {
    private static final Function<Setting, Duration> DURATION_PARSER = setting -> Duration.parse(setting.getValue());

    public static final SettingKey<Duration> LINK_TTL = key("linkTtl", DURATION_PARSER);


    private static <T> SettingKey<T> key(String name, Function<Setting, T> parser) {
        return new SettingKey<>(name, parser);
    }

}
