package ru.chernov.urlshortener.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import ru.chernov.urlshortener.config.properties.RedisProperties;

import java.io.IOException;


@Configuration
public class RedisConfig {
    private final Resource configFile;
    private final RedisProperties redisProperties;


    public RedisConfig(@Value("classpath:/redis/redisson_config.yaml") Resource configFile,
                       RedisProperties redisProperties) {
        this.configFile = configFile;
        this.redisProperties = redisProperties;
    }


    @Bean
    public RedissonClient redissonClient() throws IOException {
        var config = Config.fromYAML(configFile.getInputStream());

        var singleServerConfig = config.useSingleServer();
        singleServerConfig.setAddress(redisProperties.getUrl());

        return Redisson.create(config);
    }

}
