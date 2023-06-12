package ru.chernov.urlshortener.service.link;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.client.codec.Codec;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.stereotype.Service;
import ru.chernov.urlshortener.dto.link.RedisLinkDto;
import ru.chernov.urlshortener.dto.setting.SettingKey;
import ru.chernov.urlshortener.service.infrastructure.RedisService;
import ru.chernov.urlshortener.service.setting.SettingService;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;


@Service
public class LinkRedisService {
    private final SettingService settingService;
    private final RedisService redisService;
    private final Codec codec;


    public LinkRedisService(SettingService settingService,
                            RedisService redisService,
                            ObjectMapper objectMapper) {
        this.settingService = settingService;
        this.redisService = redisService;
        this.codec = new TypedJsonJacksonCodec(String.class, RedisLinkDto.class, objectMapper);
    }


    public Optional<RedisLinkDto> read(String shortLink) {
        return redisService.getValue(getKey(shortLink), codec);
    }


    public void write(String shortLink, String link, UUID token) {
        RedisLinkDto redisLink = new RedisLinkDto();
        redisLink.setLink(link);
        redisLink.setToken(token);

        Duration redisTtl = settingService.get(SettingKey.LINK_TTL);
        redisService.putValue(getKey(shortLink), codec, redisLink, redisTtl);
    }


    private String getKey(String shortLink) {
        return "links:" + shortLink;
    }

}
