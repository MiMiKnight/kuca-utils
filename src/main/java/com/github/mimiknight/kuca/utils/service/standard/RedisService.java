package com.github.mimiknight.kuca.utils.service.standard;

import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.data.redis.connection.DataType;

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
 * Redis工具类接口
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-03-24 20:46:09
 */
public interface RedisService {

    /**
     * 删除指定key的缓存
     *
     * @param key 键
     */
    void delete(String key);

    /**
     * 批量删除指定key的缓存
     *
     * @param keys 键的集合
     */
    void delete(Collection<String> keys);

    /**
     * 是否存在指定Key的缓存
     *
     * @param key 键
     * @return boolean
     */
    Boolean hasKey(String key);

    /**
     * 查找匹配的key的缓存
     *
     * @param pattern key的正则表达式
     * @return {@link Set}<{@link String}>
     */
    Set<String> keys(String pattern);

    /**
     * 将当前数据库指定key的缓存，移动到给定的数据库db当中
     *
     * @param key     键
     * @param dbIndex 缓存库索引
     * @return {@link Boolean}
     */
    Boolean move(String key, int dbIndex);

    /**
     * 持久化指定Key缓存
     *
     * @param key 键
     * @return {@link Boolean}
     */
    Boolean persist(String key);

    /**
     * 设置过期时间
     *
     * @param key        键
     * @param expireTime 过期时间
     * @param unit       时间单位
     * @return {@link Boolean}
     */
    Boolean expire(String key, long expireTime, TimeUnit unit);

    /**
     * 设置过期时间
     *
     * @param key      键
     * @param duration 持续时间
     * @return {@link Boolean}
     */
    Boolean expire(String key, Duration duration);

    /**
     * 设置过期时间
     *
     * @param key  键
     * @param date 缓存过期日期
     * @return {@link Boolean}
     */
    Boolean expireAt(String key, Date date);

    /**
     * 设置过期时间
     *
     * @param key     键
     * @param instant 缓存过期时刻
     * @return {@link Boolean}
     */
    Boolean expireAt(String key, Instant instant);

    /**
     * 设置过期时间
     *
     * @param key      键
     * @param dateTime 缓存过期日期
     * @return {@link Boolean}
     */
    Boolean expireAt(String key, ZonedDateTime dateTime);

    /**
     * 返回指定key缓存的剩余的过期时间
     *
     * @param key  键
     * @param unit 时间单位
     * @return {@link Long} 时间
     */
    Long getExpire(String key, TimeUnit unit);

    /**
     * 返回指定key缓存的剩余的过期时间
     * <p>
     * 默认时间单位：毫秒
     *
     * @param key 键
     * @return {@link Long} 时间
     */
    Long getExpire(String key);

    /**
     * 从当前数据库中随机返回一个 key
     *
     * @return {@link String}
     */
    String randomKey();

    /**
     * 重命名（不推荐使用）
     *
     * @param oldKey 旧键名
     * @param newKey 新键名
     */
    void rename(String oldKey, String newKey);

    /**
     * 重命名
     * <p>
     * 仅当 newkey 不存在时，将 oldKey 改名为 newkey
     *
     * @param oldKey 旧键名
     * @param newKey 新键名
     */
    void renameIfAbsent(String oldKey, String newKey);

    /**
     * 返回 key 所储存的值的类型
     *
     * @param key 键
     * @return {@link DataType}
     */
    DataType type(String key);

    //-------------------String相关操作---------------------//

    /**
     * 设置缓存
     *
     * @param key   键
     * @param value 值
     */
    void set(String key, String value);

    /**
     * 设置缓存
     *
     * @param key   键
     * @param value 值
     */
    <T> void set(String key, T value);

    /**
     * 设置缓存
     *
     * @param key        键
     * @param value      值
     * @param expireTime 缓存过期时间
     * @param unit       时间单位
     */
    void set(String key, String value, long expireTime, TimeUnit unit);

    /**
     * 设置缓存
     *
     * @param key        键
     * @param value      值
     * @param expireTime 缓存过期时间
     * @param unit       时间单位
     */
    <T> void set(String key, T value, long expireTime, TimeUnit unit);


    /**
     * key不存在时，才设置该缓存
     *
     * @param key        键
     * @param value      值
     * @param expireTime 缓存过期时间
     * @param unit       时间单位
     * @return {@link Boolean} 之前Key已经存在返回false,Key之前不存在返回true
     */
    Boolean setIfAbsent(String key, String value, long expireTime, TimeUnit unit);

    /**
     * key不存在时，才设置该缓存
     *
     * @param key        键
     * @param value      值
     * @param expireTime 缓存过期时间
     * @param unit       时间单位
     * @return {@link Boolean} 之前Key已经存在返回false,Key之前不存在返回true
     */
    <T> Boolean setIfAbsent(String key, T value, long expireTime, TimeUnit unit);

    /**
     * 获取缓存
     *
     * @param key 键
     * @return {@link String}
     */
    String get(String key);

    /**
     * 获取缓存
     *
     * @param key         键
     * @param returnClass 返回值Class类型
     * @return {@link T}
     */
    <T> T get(String key, Class<T> returnClass);

    /**
     * 获取缓存
     *
     * @param key           键
     * @param typeReference 待转换类型
     * @return {@link T}
     */
    <T> T get(String key, TypeReference<T> typeReference);

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
     *
     * @param key   键
     * @param value 值
     * @return {@link String}
     */
    String getAndSet(String key, String value);

    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
     *
     * @param key         键
     * @param value       值
     * @param returnClass 返回值Class类型
     * @return {@link T}
     */
    <T> T getAndSet(String key, T value, Class<T> returnClass);

    /**
     * 返回key中字符串值的子字符
     *
     * @param key   键
     * @param start 开始位置
     * @param end   结束位置
     * @return {@link String}
     */
    String getRange(String key, long start, long end);

    /**
     * 设置bit位的值
     * <p>
     * 设置ASCII码, 字符串'a'的ASCII码是97, 转为二进制是'01100001', 此方法是将二进制第offset位值变为value
     *
     * @param key    键
     * @param offset 偏移量
     * @param value  值，true为1, false为0
     * @return boolean
     */
    Boolean setBit(String key, long offset, boolean value);

    /**
     * 获取key中字符串的长度
     *
     * @param key 关键
     * @return {@link Long}
     */
    Long size(String key);

    /**
     * 批量添加
     *
     * @param maps 集合
     */
    void multiSet(Map<String, String> maps);

    /**
     * 批量添加，当且仅当所有给定 key 都不存在
     *
     * @param maps 集合
     * @return boolean
     */
    Boolean multiSetIfAbsent(Map<String, String> maps);

    /**
     * 自增或者自减
     *
     * @param key       键
     * @param increment 增量
     * @return {@link Long}
     */
    Long increase(String key, long increment);

    /**
     * 自增或者自减
     *
     * @param key       键
     * @param increment 增量
     * @return {@link Double}
     */
    Double increase(String key, double increment);

    /**
     * 追加到末尾
     *
     * @param key   键
     * @param value 值
     * @return {@link Integer}
     */
    Integer append(String key, String value);

    //-------------------hash相关操作-------------------------//

    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key   关键
     * @param field 字段名称
     * @return {@link Object}
     */
    Object hGet(String key, String field);

    /**
     * 获取所有给定字段的值
     *
     * @param key    键
     * @param fields 字段名称集合
     * @return {@link List}<{@link Object}>
     */
    List<Object> hMultiGet(String key, Collection<Object> fields);

    /**
     * 设置哈希
     *
     * @param key     键
     * @param hashKey 哈希键
     * @param value   值
     */
    void hPut(String key, String hashKey, String value);

    /**
     * 批量设置哈希
     *
     * @param key  键
     * @param maps 集合
     */
    void hPutAll(String key, Map<String, String> maps);

    /**
     * 设置哈希，仅当hashKey不存在时
     *
     * @param key     键
     * @param hashKey 哈希键
     * @param value   值
     * @return {@link Boolean}
     */
    Boolean hPutIfAbsent(String key, String hashKey, String value);

    /**
     * 删除一个或多个哈希表字段
     *
     * @param key    键
     * @param fields 哈希字段数组
     * @return {@link Long}
     */
    Long hDelete(String key, Object... fields);

    /**
     * 指定的哈希字段是否存在
     *
     * @param key   键
     * @param field 哈希字段
     * @return boolean
     */
    Boolean hasKey(String key, String field);

    /**
     * 为哈希表 key 中的指定字段的值加上增量 increment
     *
     * @param key       键
     * @param field     哈希字段
     * @param increment 增量
     * @return {@link Long}
     */
    Long increase(String key, Object field, long increment);

    /**
     * 为哈希表 key 中的指定字段的值加上增量 increment
     *
     * @param key       键
     * @param field     哈希字段
     * @param increment 增量
     * @return {@link Double}
     */
    Double increase(String key, Object field, double increment);

    /**
     * 获取所有哈希表中的字段
     *
     * @param key 关键
     * @return {@link Set}<{@link Object}>
     */
    Set<Object> hKeys(String key);

    //-------------------List相关操作-------------------------//
    //-------------------Set相关操作--------------------------//
    //-------------------ZSet相关操作-------------------------//
}
