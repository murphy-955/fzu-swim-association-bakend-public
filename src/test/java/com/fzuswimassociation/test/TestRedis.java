package com.fzuswimassociation.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 *
 * @author 李泽聿
 * @since 2025-09-09 21:26
 */

@SpringBootTest
public class TestRedis {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("name", "fzuswim");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
}
