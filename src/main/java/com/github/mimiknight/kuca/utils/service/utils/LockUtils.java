package com.github.mimiknight.kuca.utils.service.utils;

import com.github.mimiknight.kuca.utils.service.standard.RedisLockService;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * 锁工具类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-08-04 19:54:39
 */
public class LockUtils {

    @Autowired
    private RedisLockService lockService;

    private static LockUtils instance;

    private LockUtils() {
    }

    @PostConstruct
    public void init() {
        instance = this;
        instance.lockService = this.lockService;
    }

    /**
     * Redis加锁
     * <p>
     * 获取不到锁则一直等待，直至锁被释放
     *
     * @param key       Redis锁键
     * @param leaseTime 上锁后自动释放锁的时间（锁失效时间）
     * @param unit      锁释放时间的单位
     */
    public static void lock(String key, long leaseTime, TimeUnit unit) {
        instance.lockService.lock(key, leaseTime, unit);
    }

    /**
     * Redis加锁
     * <p>
     * 获取不到锁则一直等待，直至锁被释放
     *
     * @param key       Redis锁键
     * @param leaseTime 上锁后自动释放锁的时间
     */
    public static void lock(String key, long leaseTime) {
        instance.lockService.lock(key, leaseTime);
    }

    /**
     * Redis加锁
     * <p>
     * 获取不到锁则一直等待，直至锁被释放
     * <p>
     * 默认释放时间30秒，如果业务线程仍在执行则启动看门狗，每10秒自动续期30秒
     * 如果业务线程终止或者睡眠则在不再续期，30秒后锁自动释放
     *
     * @param key Redis锁键
     */
    public void lock(String key) {
        instance.lockService.lock(key);
    }

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
     * @param key Redis锁键
     * @return boolean
     */
    public static boolean tryLock(String key) {
        return instance.lockService.tryLock(key);
    }

    /**
     * Redis加锁
     * <p>
     * 如果获取锁成功，则返回true；如果在等待时间内未获取到锁，则返回false
     * <p>
     * 默认释放时间30秒，如果业务线程仍在执行则启动看门狗，每10秒自动续期30秒
     * 如果业务线程终止或者睡眠则在不再续期，30秒后锁自动释放
     *
     * @param key      Redis锁键
     * @param waitTime 等待获取锁的时间
     * @param unit     等待时间的单位
     * @return boolean
     */
    public static boolean tryLock(String key, long waitTime, TimeUnit unit) {
        return instance.lockService.tryLock(key, waitTime, unit);
    }

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
     * @param key      Redis锁键
     * @param waitTime 等待获取锁的时间
     * @return boolean
     */
    public static boolean tryLock(String key, long waitTime) {
        return instance.lockService.tryLock(key, waitTime);
    }

    /**
     * Redis加锁
     * <p>
     * 如果获取锁成功，则返回true；如果在等待时间内未获取到锁，则返回false
     *
     * @param key       Redis锁键
     * @param waitTime  等待获取锁的时间
     * @param leaseTime 上锁后自动释放锁的时间（锁失效时间）
     * @param unit      时间单位
     * @return boolean
     */
    public static boolean tryLock(String key, long waitTime, long leaseTime, TimeUnit unit) {
        return instance.lockService.tryLock(key, waitTime, leaseTime, unit);
    }

    /**
     * Redis加锁
     * <p>
     * 如果获取锁成功，则返回true；如果在等待时间内未获取到锁，则返回false
     * <p>
     * 默认时间单位：秒
     *
     * @param key       Redis锁键
     * @param waitTime  等待获取锁的时间
     * @param leaseTime 上锁后自动释放锁的时间（锁失效时间）
     * @return boolean
     */
    public static boolean tryLock(String key, long waitTime, long leaseTime) {
        return instance.lockService.tryLock(key, waitTime, leaseTime);
    }

    /**
     * 释放锁
     * <p>
     * 若没用锁情况下，就不调用释放锁的代码，若有锁情况下才调用释放锁
     *
     * @param lock Redis锁对象
     */
    public static void unlock(RLock lock) {
        instance.lockService.unlock(lock);
    }

    /**
     * 释放锁
     * <p>
     * 若没用锁情况下，就不调用释放锁的代码，若有锁情况下才调用释放锁
     *
     * @param key Redis锁键
     */
    public static void unlock(String key) {
        instance.lockService.unlock(key);
    }
}
