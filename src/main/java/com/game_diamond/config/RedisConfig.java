package com.game_diamond.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@Configuration
public class RedisConfig {
    @Autowired
    private AppConfig appConfig;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        log.info("Init jedis connection");
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(appConfig.getHostNameRedis(), appConfig.getPortRedis());
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }
}
