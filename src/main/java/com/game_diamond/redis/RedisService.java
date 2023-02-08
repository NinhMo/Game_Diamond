package com.game_diamond.redis;

import java.util.concurrent.TimeUnit;

public interface RedisService {
    Object findByKey(String key);
    void save(String key, Object value, Long time, TimeUnit timeUnit);
    void remove(String key);
}
