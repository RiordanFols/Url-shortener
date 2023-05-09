package ru.chernov.urlshortener.service.infrastructure;

import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.Codec;
import org.springframework.stereotype.Component;
import ru.chernov.urlshortener.config.properties.RedisProperties;

import java.time.Duration;
import java.util.Optional;


@Component
public class RedisService {
    private static final String VALUE = "value";

    private final RedissonClient redissonClient;
    private final RedisProperties redisProperties;


    public RedisService(RedissonClient redissonClient, RedisProperties redisProperties) {
        this.redissonClient = redissonClient;
        this.redisProperties = redisProperties;
    }


    public <V> RMapCache<String, V> getMapCache(String key, Codec codec) {
        String redisKey = composeKey(key);
        return redissonClient.getMapCache(redisKey, codec);
    }


    public <V> Optional<V> getValue(String key, Codec codec) {
        RMapCache<String, V> mapCache = getMapCache(key, codec);
        return Optional.ofNullable(mapCache.get(VALUE));
    }


    public <V> void putValue(String key, Codec codec, V value, Duration duration) {
        var mapCache = getMapCache(key, codec);
        mapCache.fastPut(VALUE, value);
        mapCache.expire(duration);
    }


    private String composeKey(String key) {
        return redisProperties.getKeyPrefix() + key;
    }

}
