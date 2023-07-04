package cn.yhm.developer.kuca.common.utils.impl;

import cn.yhm.developer.kuca.common.exception.RedisException;
import cn.yhm.developer.kuca.common.utils.standard.RedisLockService;
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
    public boolean lock(String lockKey, TimeUnit unit, long leaseTime) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            lock.lock(leaseTime, unit);
            return true;
        } catch (Exception e) {
            log.error("Redis exception,error = {}", e.getMessage());
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
            log.error("Redis exception,error = {}", e.getMessage());
            throw new RedisException("Redis exception");
        }
    }

    @Override
    public boolean lock(String lockKey) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            lock.lock();
            return true;
        } catch (Exception e) {
            log.error("Redis exception,error = {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean tryLock(String lockKey) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            return lock.tryLock();
        } catch (Exception e) {
            log.error("Redis exception,error = {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean tryLock(String lockKey, TimeUnit unit, long waitTime) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            return lock.tryLock(waitTime, unit);
        } catch (Exception e) {
            log.error("Redis exception,error = {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean tryLock(String lockKey, long waitTime) {
        return tryLock(lockKey, TimeUnit.SECONDS, waitTime);
    }

    @Override
    public boolean tryLock(String lockKey, TimeUnit unit, long waitTime, long leaseTime) {
        try {
            RLock lock = redissonClient.getLock(lockKey);
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (Exception e) {
            log.error("Redis exception,error = {}", e.getMessage());
            return false;
        }
    }

    @Override
    public boolean tryLock(String lockKey, long waitTime, long leaseTime) {
        return tryLock(lockKey, TimeUnit.SECONDS, waitTime, leaseTime);
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
            log.error("Redis exception,error = {}", e.getMessage());
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
            log.error("Redis exception,error = {}", e.getMessage());
        }
    }
}
