package com.github.mimiknight.kuca.common.utils.standard;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类接口
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-03-24 20:47:30
 */
public interface DateTimeService {

    /**
     * 日期字符串转ZonedDateTime
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @param locale 语言环境
     * @return {@link ZonedDateTime}
     */
    ZonedDateTime toZonedDateTime(String date, String format, Locale locale);

    /**
     * 日期字符串转ZonedDateTime
     * <p>
     * 默认English语言环境
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @return {@link ZonedDateTime}
     */
    ZonedDateTime toZonedDateTime(String date, String format);

    /**
     * 日期字符串转OffsetDateTime
     * <p>
     * 默认语言环境：English
     * <p>
     * 默认字符串日期格式：yyyy-MM-dd HH:mm:ss.SSS z
     * <p>
     * 2022-09-04 10:06:39.123 GMT+08:00 【表示 东八区 2022年9月4日10点6分39秒123毫秒】
     *
     * @param date 日期字符串
     * @return {@link ZonedDateTime}
     */
    ZonedDateTime toZonedDateTime(String date);

    /**
     * 日期字符串转Date
     *
     * @param date    日期字符串
     * @param format  日期格式
     * @param zone    字符串日期所在的时区（此处设置的时区可能会由于调用parse()解析方法而被覆盖）
     * @param local   语言环境
     * @param lenient 宽容模式（true：宽容模式，false：严格模式）
     * @return {@link Date}
     */
    Date toDate(String date, String format, TimeZone zone, boolean lenient, Locale local);

    /**
     * 日期字符串转Date
     * <p>
     * 默认：严格模式
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @param zone   字符串日期所在的时区（此处设置的时区可能会由于调用parse()解析方法而被覆盖）
     * @param local  语言环境
     * @return {@link Date}
     */
    Date toDate(String date, String format, TimeZone zone, Locale local);

    /**
     * 日期字符串转Date
     * <p>
     * 默认语言环境：English
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @param zone   字符串日期所在的时区（此处设置的时区可能会由于调用parse()解析方法而被覆盖）
     * @return {@link Date}
     */
    Date toDate(String date, String format, TimeZone zone);

    /**
     * 日期字符串转Date
     * <p>
     * 默认语言环境：English
     *
     * @param date    日期字符串
     * @param format  日期格式
     * @param zoneGMT 字符串日期所在的时区（此处设置的时区可能会由于调用parse()解析方法而被覆盖）
     * @return {@link Date}
     */
    Date toDate(String date, String format, String zoneGMT);

    /**
     * 日期字符串转Date
     * <p>
     * 默认语言环境：English
     * <p>
     * 默认format：yyyy-MM-dd HH:mm:ss
     * <p>
     * 默认时区：GMT+00:00
     *
     * @param date 日期字符串
     * @return {@link Date}
     */
    Date toDate(String date);

    /**
     * 获取UTC时间
     *
     * @return {@link LocalDateTime}
     */
    LocalDateTime utcLocalDateTime();

    /**
     * 获取UTC时间
     *
     * @return {@link ZonedDateTime}
     */
    ZonedDateTime utcZonedDateTime();

}
