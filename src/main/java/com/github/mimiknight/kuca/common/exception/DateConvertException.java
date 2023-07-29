package com.github.mimiknight.kuca.common.exception;

/**
 * 日期转换异常类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-07-29 19:00:32
 */
public class DateConvertException extends RuntimeException {

    public DateConvertException() {
        super();
    }


    public DateConvertException(String s) {
        super(s);
    }


    public DateConvertException(String message, Throwable cause) {
        super(message, cause);
    }


    public DateConvertException(Throwable cause) {
        super(cause);
    }
}
