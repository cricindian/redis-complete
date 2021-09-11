package com.example.redis.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil<T> {
    private RedisTemplate<String, T> redisTemplate;
    private HashOperations<String, Object, T> hashOperation;
    private ListOperations<String, T> listOperation;
    private ValueOperations<String, T> valueOperations;

    @Autowired
    RedisUtil(RedisTemplate<String, T> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperation = redisTemplate.opsForHash();
        this.listOperation = redisTemplate.opsForList();
        this.valueOperations = redisTemplate.opsForValue();
    }

    public void putMap(String redisKey, Object key, T data) {
        hashOperation.put(redisKey, key, data);
    }

    public void delete(String redisKey, Object key) {
        hashOperation.delete(redisKey, key);
    }

    public void putAll(String key, Map map){
        hashOperation.putAll(key, map);
    }

    public T getMapAsSingleEntry(String redisKey, Object key) {
        return hashOperation.get(redisKey, key);
    }

    public Map<Object, T> getMapAsAll(String redisKey) {
        return hashOperation.entries(redisKey);
    }

    public void putValue(String key, T value) {
        valueOperations.set(key, value);
    }

    public void putValueWithExpireTime(String key, T value, long timeout, TimeUnit unit) {
        valueOperations.setIfAbsent(key, value, timeout, unit);
    }

    public T getValue(String key) {
        return valueOperations.get(key);
    }

    public void setExpire(String key, long timeout, TimeUnit unit) {
        redisTemplate.expire(key, timeout, unit);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public Set<String> getAllKey(String pattern) {
        return redisTemplate.keys(pattern);
    }
}