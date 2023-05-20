package ru.chernov.urlshortener.dto.setting;

import ru.chernov.urlshortener.entity.Setting;
import ru.chernov.urlshortener.enums.setting.SettingType;

import java.time.Duration;
import java.util.function.Function;


public record SettingKey<T>(String name, SettingType settingType, Function<Setting, T> parser) {
    private static final Function<Setting, Duration> DURATION_PARSER = setting -> Duration.parse(setting.getValue());

    public static final SettingKey<Duration> LINK_TTL = durationKey("linkTtl");


    private static SettingKey<Duration> durationKey(String name) {
        return new SettingKey<>(name, SettingType.DURATION, DURATION_PARSER);
    }

}