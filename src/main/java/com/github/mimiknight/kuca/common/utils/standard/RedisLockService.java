package com.github.mimiknight.kuca.common.utils.standard;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * Redisson分布式锁工具类接口
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-09 22:21:01
 */
public interface RedisLockService {

    /**
     * Redis加锁
     * <p>
     * 获取不到锁则一直等待，直至锁被释放
     *
     * @param lockKey   Redis锁键
     * @param unit      锁释放时间的单位
     * @param leaseTime 上锁后自动释放锁的时间（锁失效时间）
     * @return {@link RLock}
     */
    boolean lock(String lockKey, TimeUnit unit, long leaseTime);

    /**
     * Redis加锁
     * <p>
     * 获取不到锁则一直等待，直至锁被释放
     * <p>
     * 默认锁释放时间单位：秒
     *
     * @param lockKey   Redis锁键
     * @param leaseTime 上锁后自动释放锁的时间
     * @return {@link RLock}
     */
    boolean lock(String lockKey, long leaseTime);

    /**
     * Redis加锁
     * <p>
     * 获取不到锁则一直等待，直至锁被释放
     * <p>
     * 默认释放时间30秒，如果业务线程仍在执行则启动看门狗，每10秒自动续期30秒
     * 如果业务线程终止或者睡眠则在不再续期，30秒后锁自动释放
     *
     * @param lockKey Redis锁键
     * @return {@link RLock}
     */
    boolean lock(String lockKey);

    /**
     * Redis加锁
     * <p>
     * 如果获取锁成功，则返回true；如果在等待时间内未获取到锁，则返回false
     * <p>
     * 默认等待时间10秒
     * <p>
     * 默认释放时间30秒，如果业务线程仍在执行则启动看门狗，每10秒自动续期30秒
     * 如果业务线程终止或者睡眠则在不再续期，30秒后锁自动释放
     *
     * @param lockKey Redis锁键
     * @return boolean
     */
    boolean tryLock(String lockKey);

    /**
     * Redis加锁
     * <p>
     * 如果获取锁成功，则返回true；如果在等待时间内未获取到锁，则返回false
     * <p>
     * 默认释放时间30秒，如果业务线程仍在执行则启动看门狗，每10秒自动续期30秒
     * 如果业务线程终止或者睡眠则在不再续期，30秒后锁自动释放
     *
     * @param lockKey  Redis锁键
     * @param waitTime 等待获取锁的时间
     * @param unit     等待时间的单位
     * @return boolean
     */
    boolean tryLock(String lockKey, long waitTime, TimeUnit unit);

    /**
     * Redis加锁 推荐使用
     * <p>
     * 如果获取锁成功，则返回true；如果在等待时间内未获取到锁，则返回false
     * <p>
     * 默认释放时间30秒，如果业务线程仍在执行则启动看门狗，每10秒自动续期30秒
     * 如果业务线程终止或者睡眠则在不再续期，30秒后锁自动释放
     * <p>
     * 默认时间单位：秒
     *
     * @param lockKey  Redis锁键
     * @param waitTime 等待获取锁的时间
     * @return boolean
     */
    boolean tryLock(String lockKey, long waitTime);

    /**
     * Redis加锁
     * <p>
     * 如果获取锁成功，则返回true；如果在等待时间内未获取到锁，则返回false
     *
     * @param lockKey   Redis锁键
     * @param waitTime  等待获取锁的时间
     * @param leaseTime 上锁后自动释放锁的时间（锁失效时间）
     * @param unit      时间单位
     * @return boolean
     */
    boolean tryLock(String lockKey, long waitTime, long leaseTime, TimeUnit unit);

    /**
     * Redis加锁
     * <p>
     * 如果获取锁成功，则返回true；如果在等待时间内未获取到锁，则返回false
     * <p>
     * 默认时间单位：秒
     *
     * @param lockKey   Redis锁键
     * @param waitTime  等待获取锁的时间
     * @param leaseTime 上锁后自动释放锁的时间（锁失效时间）
     * @return boolean
     */
    boolean tryLock(String lockKey, long waitTime, long leaseTime);

    /**
     * 释放锁
     * <p>
     * 若没用锁情况下，就不调用释放锁的代码，若有锁情况下才调用释放锁
     *
     * @param lockKey Redis锁键
     */
    void unlock(String lockKey);

    /**
     * 释放锁
     * <p>
     * 若没用锁情况下，就不调用释放锁的代码，若有锁情况下才调用释放锁
     *
     * @param lock Redis锁对象
     */
    void unlock(RLock lock);
}
