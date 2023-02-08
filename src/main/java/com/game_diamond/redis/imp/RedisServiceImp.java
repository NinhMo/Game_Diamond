package com.game_diamond.redis.imp;

import com.game_diamond.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisServiceImp implements RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate2;

    private ValueOperations<String, Object> valueOperations;

    public RedisServiceImp() {
    }

    @PostConstruct
    private void init() {
        valueOperations = redisTemplate2.opsForValue();
    }

    @Override
    public Object findByKey(String key) {
        Object object = null;
        try {
            object = valueOperations.get(key);
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
        return object;
    }
    @Override
    public void save(String key, Object value, Long time, TimeUnit timeUnit) {
        valueOperations.set(key, value, time, timeUnit);
    }
    @Override
    public void remove(String key) {
        redisTemplate2.delete(key);
    }
}
