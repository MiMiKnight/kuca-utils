package com.github.mimiknight.kuca.utils.service.utils;

import com.github.mimiknight.kuca.utils.service.standard.RedisService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Redis静态工具类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @date 2023-08-05 12:25:48
 * @since 0.0.1-SNAPSHOT
 */
public class RedisUtils {
    @Autowired
    private RedisService redisService;

    private static RedisUtils instance;

    private RedisUtils() {
    }

    @PostConstruct
    public void init() {
        instance = this;
        instance.redisService = this.redisService;
    }

}
