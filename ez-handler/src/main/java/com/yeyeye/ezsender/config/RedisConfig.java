package com.yeyeye.ezsender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author yeyeye
 * @Date 2023/5/2 16:21
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        StringRedisSerializer strSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(strSerializer);
        redisTemplate.setValueSerializer(strSerializer);
        return redisTemplate;
    }
}
