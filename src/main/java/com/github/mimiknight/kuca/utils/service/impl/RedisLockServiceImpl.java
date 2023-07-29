package com.github.mimiknight.kuca.utils.service.impl;

import com.github.mimiknight.kuca.utils.service.standard.RedisLockService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Redisson分布式锁工具类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-09 22:20:47
 */
@Slf4j
@Component
public class RedisLockServiceImpl implements RedisLockService {

    private RedissonClient redissonClient;

    @Autowired
    public void setRedissonClient(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public void lock(String lockKey, long leaseTime, TimeUnit unit) {
        redissonClient.getLock(lockKey).lock(leaseTime, unit);
    }

    @Override
    public void lock(String lockKey, long leaseTime) {
        redissonClient.getLock(lockKey).lock(leaseTime, TimeUnit.SECONDS);
    }

    @Override
    public void lock(String lockKey) {
        redissonClient.getLock(lockKey).lock();
    }

    @Override
    public boolean tryLock(String lockKey) {
        return redissonClient.getLock(lockKey).tryLock();
    }

    @Override
    public boolean tryLock(String lockKey, long waitTime, TimeUnit unit) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            return lock.tryLock(waitTime, unit);
        } catch (InterruptedException e) {
            log.error("Redis interrupted exception,error = {}", e.getMessage());
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @Override
    public boolean tryLock(String lockKey, long waitTime) {
        return tryLock(lockKey, waitTime, TimeUnit.SECONDS);
    }

    @Override
    public boolean tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (InterruptedException e) {
            log.error("Redis interrupted exception,error = {}", e.getMessage());
            Thread.currentThread().interrupt();
            return false;
        }
    }

    @Override
    public boolean tryLock(String lockKey, long waitTime, long leaseTime) {
        return tryLock(lockKey, waitTime, leaseTime, TimeUnit.SECONDS);
    }

    @Override
    public void unlock(RLock lock) {
        if (null == lock || !lock.isLocked()) {
            return;
        }
        lock.unlock();
    }

    @Override
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        unlock(lock);
    }
}
