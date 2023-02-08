package com.game_diamond.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "game.diamond")
public class AppConfig {
    private String hostNameRedis;
    private Integer portRedis;
}
