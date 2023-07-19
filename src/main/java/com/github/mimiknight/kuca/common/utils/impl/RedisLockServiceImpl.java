package com.github.mimiknight.kuca.common.utils.impl;

import com.github.mimiknight.kuca.common.utils.standard.RedisLockService;
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


    interface LocalConstant {
        String LOG_MSG_001 = "Redis exception,error = {}";
    }

    @Override
    public boolean lock(String lockKey, TimeUnit unit, long leaseTime) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            lock.lock(leaseTime, unit);
            return true;
        } catch (Exception e) {
            log.error(LocalConstant.LOG_MSG_001, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean lock(String lockKey, long leaseTime) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            lock.lock(leaseTime, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error(LocalConstant.LOG_MSG_001, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean lock(String lockKey) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            lock.lock();
            return true;
        } catch (Exception e) {
            log.error(LocalConstant.LOG_MSG_001, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean tryLock(String lockKey) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            return lock.tryLock();
        } catch (Exception e) {
            log.error(LocalConstant.LOG_MSG_001, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean tryLock(String lockKey, long waitTime, TimeUnit unit) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            return lock.tryLock(waitTime, unit);
        } catch (InterruptedException e) {
            log.error(LocalConstant.LOG_MSG_001, e.getMessage());
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            log.error(LocalConstant.LOG_MSG_001, e.getMessage());
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
        } catch (Exception e) {
            log.error(LocalConstant.LOG_MSG_001, e.getMessage());
            return false;
        }
    }

    @Override
    public boolean tryLock(String lockKey, long waitTime, long leaseTime) {
        return tryLock(lockKey, waitTime, leaseTime, TimeUnit.SECONDS);
    }

    @Override
    public void unlock(String lockKey) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            if (!lock.isLocked()) {
                return;
            }
            lock.unlock();
        } catch (Exception e) {
            log.error("Redis unlock failed, error = {}", e.getMessage());
        }
    }

    @Override
    public void unlock(RLock lock) {
        try {
            if (!lock.isLocked()) {
                return;
            }
            lock.unlock();
        } catch (Exception e) {
            log.error("Redis unlock failed, error = {}", e.getMessage());
        }
    }
}
