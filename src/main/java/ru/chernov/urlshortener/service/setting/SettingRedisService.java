package ru.chernov.urlshortener.service.setting;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.client.codec.Codec;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.dto.setting.SettingKey;
import ru.chernov.urlshortener.service.infrastructure.RedisService;

import java.time.Duration;
import java.util.Optional;


@Service
public class SettingRedisService {
    private final RedisService redisService;
    private final Codec codec;


    public SettingRedisService(RedisService redisService, ObjectMapper objectMapper) {
        this.redisService = redisService;
        this.codec = new TypedJsonJacksonCodec(String.class, String.class, objectMapper);
    }


    public Optional<String> read(SettingKey<?> settingKey) {
        return redisService.getValue(getKey(settingKey), codec);
    }


    public void write(SettingKey<?> settingKey, String value) {
        // TODO: duration from config
        redisService.putValue(getKey(settingKey), codec, value, Duration.ofDays(1));
    }


    private String getKey(SettingKey<?> settingKey) {
        return "settings:" + settingKey.name();
    }

}
