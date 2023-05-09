package ru.chernov.urlshortener.service.link;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.client.codec.Codec;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.service.infrastructure.RedisService;

import java.time.Duration;
import java.util.Optional;


@Service
public class LinkRedisService {
    private final RedisService redisService;
    private final Codec codec;


    public LinkRedisService(RedisService redisService, ObjectMapper objectMapper) {
        this.redisService = redisService;
        this.codec = new TypedJsonJacksonCodec(String.class, String.class, objectMapper);
    }


    public Optional<String> read(String shortLink) {
        return redisService.getValue(getKey(shortLink), codec);
    }


    public void write(String shortLink, String link) {
        // TODO: redis ttl from user setting
        Duration redisTtl = Duration.ofDays(1);
        redisService.putValue(getKey(shortLink), codec, link, redisTtl);
    }


    private String getKey(String shortLink) {
        return "links:" + shortLink;
    }

}
