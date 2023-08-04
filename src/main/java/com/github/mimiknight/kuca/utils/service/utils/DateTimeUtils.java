package com.github.mimiknight.kuca.utils.service.utils;

import com.github.mimiknight.kuca.utils.service.standard.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期时间工具类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-08-04 20:03:36
 */
public class DateTimeUtils {

    @Autowired
    private DateTimeService dateTimeService;

    private static DateTimeUtils instance;

    private DateTimeUtils() {
    }

    @PostConstruct
    public void init() {
        instance = this;
        instance.dateTimeService = this.dateTimeService;
    }

    /**
     * 日期字符串转ZonedDateTime
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @param locale 语言环境
     * @return {@link ZonedDateTime}
     */
    public static ZonedDateTime toZonedDateTime(String date, String format, Locale locale) {
        return instance.dateTimeService.toZonedDateTime(date, format, locale);
    }

    /**
     * 日期字符串转ZonedDateTime
     * <p>
     * 默认English语言环境
     *
     * @param date   日期字符串
     * @param format 日期格式
     * @return {@link ZonedDateTime}
     */
    public static ZonedDateTime toZonedDateTime(String date, String format) {
        return instance.dateTimeService.toZonedDateTime(date, format);
    }

    /**
     * 日期字符串转OffsetDateTime
     * <p>
     * 默认语言环境：English
     * <p>
     * 默认字符串日期格式：yyyy-MM-dd HH:mm:ss.SSS XXX
     * <p>
     * 案例：
     * <p>
     * 2022-09-04 10:06:39.123 +08:00 【表示 东八区 2022年9月4日10点06分39秒123毫秒】
     * <p>
     * 2022-09-04 10:06:39.123 Z      【表示 零时区 2022年9月4日10点06分39秒123毫秒】
     * <p>
     * 2022-09-04 10:06:39.123 -06:00 【表示 西六区 2022年9月4日10点06分39秒123毫秒】
     *
     * @param date 日期字符串
     * @return {@link ZonedDateTime}
     */
    public static ZonedDateTime toZonedDateTime(String date) {
        return instance.dateTimeService.toZonedDateTime(date);
    }

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
    public static Date toDate(String date, String format, TimeZone zone, boolean lenient, Locale local) {
        return instance.dateTimeService.toDate(date, format, zone, lenient, local);
    }

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
    public static Date toDate(String date, String format, TimeZone zone, Locale local) {
        return instance.dateTimeService.toDate(date, format, zone, local);
    }

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
    public static Date toDate(String date, String format, TimeZone zone) {
        return instance.dateTimeService.toDate(date, format, zone);
    }

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
    public static Date toDate(String date, String format, String zoneGMT) {
        return instance.dateTimeService.toDate(date, format, zoneGMT);
    }

    /**
     * 日期字符串转Date
     * <p>
     * 默认语言环境：English
     * <p>
     * 默认format：yyyy-MM-dd HH:mm:ss
     * <p>
     * 默认时区：GMT（零时区）
     *
     * @param date 日期字符串
     * @return {@link Date}
     */
    public static Date toDate(String date) {
        return instance.dateTimeService.toDate(date);
    }

    /**
     * 获取UTC时间
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime utcLocalDateTime() {
        return instance.dateTimeService.utcLocalDateTime();
    }

    /**
     * 获取UTC时间
     *
     * @return {@link ZonedDateTime}
     */
    public static ZonedDateTime utcZonedDateTime() {
        return instance.dateTimeService.utcZonedDateTime();
    }
}
