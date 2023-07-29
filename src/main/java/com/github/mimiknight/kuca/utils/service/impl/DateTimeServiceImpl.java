package com.github.mimiknight.kuca.utils.service.impl;

import com.github.mimiknight.kuca.utils.constant.DateTimeFormatStandard;
import com.github.mimiknight.kuca.utils.constant.TimeZoneGMT;
import com.github.mimiknight.kuca.utils.exception.DateConvertException;
import com.github.mimiknight.kuca.utils.service.standard.DateTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期工具类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-07 08:56:34
 */
@Slf4j
@Component
public class DateTimeServiceImpl implements DateTimeService {

    @Override
    public ZonedDateTime toZonedDateTime(String date, String format, Locale locale) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, locale);
        return ZonedDateTime.parse(date, formatter);
    }

    @Override
    public ZonedDateTime toZonedDateTime(String date, String format) {
        return toZonedDateTime(date, format, Locale.ENGLISH);
    }

    @Override
    public ZonedDateTime toZonedDateTime(String date) {
        return toZonedDateTime(date, DateTimeFormatStandard.STANDARD_6, Locale.ENGLISH);
    }

    @Override
    public Date toDate(String date, String format, TimeZone zone, boolean lenient, Locale local) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format, local);
            // 设置宽松模式
            dateFormat.setLenient(lenient);
            // 字符串日期所在的时区 (由此方法设置的TimeZone可能会由于调用parse()解析方法而被覆盖。)
            dateFormat.setTimeZone(zone);
            return dateFormat.parse(date);

        } catch (ParseException e) {
            log.error("Failed to convert date string to Date object.");
            throw new DateConvertException("Failed to convert date string to Date object",e);
        }
    }

    @Override
    public Date toDate(String date, String format, TimeZone zone, Locale local) {
        return toDate(date, format, zone, false, local);
    }

    @Override
    public Date toDate(String date, String format, TimeZone zone) {
        return toDate(date, format, zone, Locale.ENGLISH);
    }

    @Override
    public Date toDate(String date, String format, String zoneGMT) {
        return toDate(date, format, TimeZone.getTimeZone(zoneGMT), Locale.ENGLISH);
    }

    @Override
    public Date toDate(String date) {
        return toDate(date, DateTimeFormatStandard.STANDARD_1, TimeZoneGMT.UK.GREENWICH);
    }

    @Override
    public LocalDateTime utcLocalDateTime() {
        return LocalDateTime.now(ZoneId.of("UTC"));
    }

    @Override
    public ZonedDateTime utcZonedDateTime() {
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }

}
