package cn.yhm.developer.kuca.common.exception;

/**
 * Redis故障异常
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
