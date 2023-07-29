package com.github.mimiknight.kuca.utils.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.mimiknight.kuca.utils.service.standard.JsonService;
import com.github.mimiknight.kuca.utils.service.standard.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-06 22:09:53
 */
@Component
public class RedisServiceImpl implements RedisService {

    private StringRedisTemplate redisTemplate;

    @Autowired
    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private JsonService jsonService;

    @Autowired
    public void setJsonService(JsonService jsonService) {
        this.jsonService = jsonService;
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    @Override
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    @Override
    public Boolean move(String key, int dbIndex) {
        return redisTemplate.move(key, dbIndex);
    }

    @Override
    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    @Override
    public Boolean expire(String key, long expireTime, TimeUnit unit) {
        return redisTemplate.expire(key, expireTime, unit);
    }

    @Override
    public Boolean expire(String key, Duration duration) {
        return redisTemplate.expire(key, duration);
    }

    @Override
    public Boolean expireAt(String key, Date date) {
        return redisTemplate.expireAt(key, date);
    }

    @Override
    public Boolean expireAt(String key, Instant instant) {
        return redisTemplate.expireAt(key, instant);
    }

    @Override
    public Boolean expireAt(String key, ZonedDateTime dateTime) {
        return redisTemplate.expireAt(key, dateTime.toInstant());
    }

    @Override
    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
    }

    @Override
    public String randomKey() {
        return redisTemplate.randomKey();
    }

    @Override
    public void rename(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    @Override
    public void renameIfAbsent(String oldKey, String newKey) {
        redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    @Override
    public DataType type(String key) {
        return redisTemplate.type(key);
    }

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public <T> void set(String key, T value) {
        String json = jsonService.toJson(value);
        this.set(key, json);
    }

    @Override
    public void set(String key, String value, long expireTime, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, expireTime, unit);
    }

    @Override
    public <T> void set(String key, T value, long expireTime, TimeUnit unit) {
        String json = jsonService.toJson(value);
        this.set(key, json, expireTime, unit);
    }

    @Override
    public Boolean setIfAbsent(String key, String value, long expireTime, TimeUnit unit) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, expireTime, unit);
    }

    @Override
    public <T> Boolean setIfAbsent(String key, T value, long expireTime, TimeUnit unit) {
        String json = jsonService.toJson(value);
        return this.setIfAbsent(key, json, expireTime, unit);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public <T> T get(String key, Class<T> returnClass) {
        String json = this.get(key);
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return jsonService.fromJson(json, returnClass);
    }

    @Override
    public <T> T get(String key, TypeReference<T> typeReference) {
        String json = this.get(key);
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return jsonService.fromJson(json, typeReference);
    }

    @Override
    public String getAndSet(String key, String value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    @Override
    public <T> T getAndSet(String key, T value, Class<T> returnClass) {
        String current = jsonService.toJson(value);
        String old = this.getAndSet(key, current);
        return jsonService.fromJson(old, returnClass);
    }

    @Override
    public String getRange(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    @Override
    public Boolean setBit(String key, long offset, boolean value) {
        return redisTemplate.opsForValue().setBit(key, offset, value);
    }

    @Override
    public Long size(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    @Override
    public void multiSet(Map<String, String> maps) {
        redisTemplate.opsForValue().multiSet(maps);
    }

    @Override
    public Boolean multiSetIfAbsent(Map<String, String> maps) {
        return redisTemplate.opsForValue().multiSetIfAbsent(maps);
    }

    @Override
    public Long increase(String key, long increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    @Override
    public Double increase(String key, double increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    @Override
    public Integer append(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    @Override
    public Object hGet(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    @Override
    public List<Object> hMultiGet(String key, Collection<Object> fields) {
        return redisTemplate.opsForHash().multiGet(key, fields);
    }

    @Override
    public void hPut(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    @Override
    public void hPutAll(String key, Map<String, String> maps) {
        redisTemplate.opsForHash().putAll(key, maps);
    }

    @Override
    public Boolean hPutIfAbsent(String key, String hashKey, String value) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    @Override
    public Long hDelete(String key, Object... fields) {
        return redisTemplate.opsForHash().delete(key, fields);
    }

    @Override
    public Boolean hasKey(String key, String field) {
        return redisTemplate.opsForHash().hasKey(key, field);
    }

    @Override
    public Long increase(String key, Object field, long increment) {
        return redisTemplate.opsForHash().increment(key, field, increment);
    }

    @Override
    public Double increase(String key, Object field, double increment) {
        return redisTemplate.opsForHash().increment(key, field, increment);
    }

    @Override
    public Set<Object> hKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

}
