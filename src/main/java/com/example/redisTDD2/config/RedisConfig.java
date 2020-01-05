package com.example.redisTDD2.config;

import com.example.redisTDD2.model.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {
    @Bean
    JedisConnectionFactory jedisConnectionFactory(){
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);
        return new JedisConnectionFactory(config);
    }

    @Bean
    RedisTemplate<String, Client> redisTemplate(){
        RedisTemplate<String, Client> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }
}
