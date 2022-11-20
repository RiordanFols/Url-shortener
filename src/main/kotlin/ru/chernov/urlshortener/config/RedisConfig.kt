package ru.chernov.urlshortener.config

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.redisson.config.SingleServerConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource
import ru.chernov.urlshortener.config.properties.RedisProperties


private const val CONFIG_YAML_PATH = "classpath:/redis/redisson_config.yaml"


@Configuration
class RedisConfig(
        @Value(CONFIG_YAML_PATH)
        private val configFile: Resource,
        private val redisProperties: RedisProperties
) {

    @Bean
    fun redissonClient(): RedissonClient {
        val config = Config.fromYAML(configFile.inputStream)

        val singleServerConfig: SingleServerConfig = config.useSingleServer()
        singleServerConfig.address = redisProperties.url

        return Redisson.create(config)
    }
}