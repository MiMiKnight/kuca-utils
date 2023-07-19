package com.github.mimiknight.kuca.common.exception;

/**
 * JSON转换异常
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-07-06 10:53:22
 */
public class JsonConvertException extends RuntimeException {

    public JsonConvertException() {
        super();
    }


    public JsonConvertException(String s) {
        super(s);
    }


    public JsonConvertException(String message, Throwable cause) {
        super(message, cause);
    }


    public JsonConvertException(Throwable cause) {
        super(cause);
    }
}
