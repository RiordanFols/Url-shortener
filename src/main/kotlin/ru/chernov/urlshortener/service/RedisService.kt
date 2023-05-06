package ru.chernov.urlshortener.service

import org.redisson.api.RMapCache
import org.redisson.api.RedissonClient
import org.redisson.client.codec.Codec
import org.springframework.stereotype.Service
import ru.chernov.urlshortener.config.properties.RedisProperties
import java.time.Duration

private const val VALUE = "value"


@Service
class RedisService(val redissonClient: RedissonClient, val redisProperties: RedisProperties) {

    fun <V> getMapCache(key: String, codec: Codec): RMapCache<String, V> {
        val redisKey: String = composeKey(key)
        return redissonClient.getMapCache(redisKey, codec)
    }


    fun <V> getValue(key: String, codec: Codec): V? {
        return getMapCache<V>(key, codec)[VALUE]
    }


    fun <V> putValue(key: String, codec: Codec, value: V, duration: Duration) {
        val mapCache = getMapCache<V>(key, codec)
        mapCache.fastPut(VALUE, value)
        mapCache.expire(duration)
    }
    

    private fun composeKey(key: String): String {
        return redisProperties.keyPrefix + key
    }

}