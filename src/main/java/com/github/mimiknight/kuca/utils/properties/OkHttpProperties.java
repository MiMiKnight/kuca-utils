package com.github.mimiknight.kuca.utils.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * OkHttp属性
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-08 22:54:34
 */
@ConfigurationProperties(prefix = "okhttp.config")
@Getter
@Setter
public class OkHttpProperties {

    /**
     * 最大空闲连接数
     */
    private Integer maxIdleConnections = 30;

    /**
     * 连接持续时间
     */
    private Long keepAliveDuration = 20000L;

    /**
     * 连接持续时间单位
     */
    private TimeUnit keepAliveDurationTimeUnit = TimeUnit.MILLISECONDS;

    /**
     * 连接超时时间
     */
    private Long connectTimeout = 30L;

    /**
     * 连接超时时间单位
     */
    private TimeUnit connectTimeoutTimeUnit = TimeUnit.MILLISECONDS;

    /**
     * 写超时时间
     */
    private Long writeTimeout = 10000L;

    /**
     * 写超时时间单位
     */
    private TimeUnit writeTimeoutTimeUnit = TimeUnit.MILLISECONDS;

    /**
     * 读超时时间
     */
    private Long readTimeout = 10000L;

    /**
     * 读超时时间单位
     */
    private TimeUnit readTimeoutTimeUnit = TimeUnit.MILLISECONDS;

    /**
     * 调用超时时间
     */
    private Integer callTimeout = 30000;

    /**
     * 调用超时时间单位
     */
    private TimeUnit callTimeoutTimeUnit = TimeUnit.MILLISECONDS;

    /**
     * 核心线程数
     */
    private Integer corePoolSize = Runtime.getRuntime().availableProcessors();

    /**
     * 最大线程数
     */
    private Integer maxPoolSize = Runtime.getRuntime().availableProcessors() * 4;

    /**
     * 重定向
     */
    private Boolean followRedirects = Boolean.FALSE;

}
