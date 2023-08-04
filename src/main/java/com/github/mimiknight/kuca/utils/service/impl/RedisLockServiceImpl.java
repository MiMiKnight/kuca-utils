package com.github.mimiknight.kuca.utils.service.impl;

import com.github.mimiknight.kuca.utils.service.standard.RedisLockService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * Redisson分布式锁工具类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-09 22:20:47
 */
@Slf4j
public class RedisLockServiceImpl implements RedisLockService {
    @Autowired
    private RedissonClient redissonClient;

    @Override
    public void lock(String key, long leaseTime, TimeUnit unit) {
        redissonClient.getLock(key).lock(leaseTime, unit);
    }

    @Override
    public void lock(String key, long leaseTime) {
        redissonClient.getLock(key).lock(leaseTime, TimeUnit.SECONDS);
    }

    @Override
    public void lock(String key) {
        redissonClient.getLock(key).lock();
    }

    @Override
    public boolean tryLock(String key) {
        return redissonClient.getLock(key).tryLock();
    }

    @Override
    public boolean tryLock(String key, long waitTime, TimeUnit unit) {
        try {
            RLock lock = redissonClient.getLock(key);
            return lock.tryLock(waitTime, unit);
        } catch (InterruptedException e) {
            log.error("Redis interrupted exception,error = {}", e.getMessage());
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @Override
    public boolean tryLock(String key, long waitTime) {
        return tryLock(key, waitTime, TimeUnit.SECONDS);
    }

    @Override
    public boolean tryLock(String key, long waitTime, long leaseTime, TimeUnit unit) {
        try {
            RLock lock = redissonClient.getLock(key);
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            log.error("Redis interrupted exception,error = {}", e.getMessage());
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @Override
    public boolean tryLock(String key, long waitTime, long leaseTime) {
        return tryLock(key, waitTime, leaseTime, TimeUnit.SECONDS);
    }

    @Override
    public void unlock(RLock lock) {
        if (null == lock || !lock.isLocked()) {
            return;
        }
        lock.unlock();
    }

    @Override
    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        unlock(lock);
    }
}
