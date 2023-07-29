package com.github.mimiknight.kuca.common.exception;

/**
 * Redis异常类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-06-03 11:32:04
 */
public class RedisException extends RuntimeException {

    public RedisException() {
        super();
    }


    public RedisException(String s) {
        super(s);
    }


    public RedisException(String message, Throwable cause) {
        super(message, cause);
    }


    public RedisException(Throwable cause) {
        super(cause);
    }
}
