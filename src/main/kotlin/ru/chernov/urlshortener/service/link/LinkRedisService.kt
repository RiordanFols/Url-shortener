package ru.chernov.urlshortener.service.link

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import org.redisson.client.codec.Codec
import org.redisson.codec.TypedJsonJacksonCodec
import org.springframework.stereotype.Service
import ru.chernov.urlshortener.service.RedisService
import java.time.Duration


@Service
class LinkRedisService(val redisService: RedisService, val objectMapper: ObjectMapper) {

    private lateinit var codec: Codec

    @PostConstruct
    fun postConstruct() {
        codec = TypedJsonJacksonCodec(String::class.java, String::class.java, objectMapper)
    }


    fun read(shortLink: String): String? {
        return redisService.getValue(getKey(shortLink), codec)
    }


    fun write(shortLink: String, link: String) {
        // TODO: redis ttl from user setting
        val redisTtl = Duration.ofDays(1)
        redisService.putValue(getKey(shortLink), codec, link, redisTtl)
    }


    private fun getKey(shortLink: String): String {
        return "links:$shortLink"
    }

}