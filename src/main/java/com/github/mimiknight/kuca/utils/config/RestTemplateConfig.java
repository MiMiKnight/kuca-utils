package com.github.mimiknight.kuca.utils.config;

import com.github.mimiknight.kuca.utils.component.http.ApiLogInterceptor;
import com.github.mimiknight.kuca.utils.component.http.CustomResponseErrorHandler;
import com.github.mimiknight.kuca.utils.properties.OkHttpProperties;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * RestTemplate配置类
 * <p>
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-06 21:44:12
 */
@EnableConfigurationProperties(OkHttpProperties.class)
@Configuration
public class RestTemplateConfig {

    private OkHttpProperties okHttpProperties;

    @Autowired
    public void setOkHttpProperties(OkHttpProperties okHttpProperties) {
        this.okHttpProperties = okHttpProperties;
    }

    /**
     * RestTemplate
     * <p>
     * BufferingClientHttpRequestFactory的 response body允许多次读取
     *
     * @param httpRequestFactory http请求工厂
     * @return {@link RestTemplate}
     */
    @Bean(name = "restTemplate")
    public RestTemplate restTemplate(ClientHttpRequestFactory httpRequestFactory) {
        return new RestTemplateBuilder()
                .interceptors(httpRequestInterceptorList())
                .errorHandler(responseErrorHandler())
                .requestFactory(() -> httpRequestFactory)
                .build();
    }

    /**
     * http请求工厂
     *
     * @param okHttpClient OkHttp客户端
     * @return {@link BufferingClientHttpRequestFactory}
     */
    @Bean("OkHttp3ClientHttpRequestFactory")
    public BufferingClientHttpRequestFactory httpRequestFactory(OkHttpClient okHttpClient) {
        OkHttp3ClientHttpRequestFactory factory = new OkHttp3ClientHttpRequestFactory(okHttpClient);
        return new BufferingClientHttpRequestFactory(factory);
    }

    /**
     * 连接池
     *
     * @return {@link ConnectionPool}
     */
    @Bean("HttpConnectionPool")
    public ConnectionPool connectionPool() {
        // 最大空闲连接
        int maxIdleConnections = okHttpProperties.getMaxIdleConnections();
        // 连接持续时间
        long keepAliveDuration = okHttpProperties.getKeepAliveDuration();
        // 连接持续时间单位
        TimeUnit keepAliveDurationTimeUnit = okHttpProperties.getKeepAliveDurationTimeUnit();

        return new ConnectionPool(maxIdleConnections, keepAliveDuration, keepAliveDurationTimeUnit);
    }

    /**
     * http客户端
     *
     * @return {@link OkHttpClient}
     */
    @Bean("OkHttp3Client")
    public OkHttpClient okHttp3Client(ConnectionPool pool) {
        // 连接超时时间
        long connectTimeout = okHttpProperties.getConnectTimeout();
        // 连接超时时间 单位
        TimeUnit connectTimeoutTimeUnit = okHttpProperties.getConnectTimeoutTimeUnit();

        // 读超时时间
        long readTimeout = okHttpProperties.getReadTimeout();
        // 读超时时间 单位
        TimeUnit readTimeoutTimeUnit = okHttpProperties.getReadTimeoutTimeUnit();

        // 写超时时间
        long writeTimeout = okHttpProperties.getWriteTimeout();
        // 写超时时间 单位
        TimeUnit writeTimeoutTimeUnit = okHttpProperties.getWriteTimeoutTimeUnit();

        // 调用超时时间
        long callTimeout = okHttpProperties.getCallTimeout();
        // 调用超时时间 单位
        TimeUnit callTimeoutTimeUnit = okHttpProperties.getCallTimeoutTimeUnit();

        // 重定向
        boolean followRedirects = okHttpProperties.getFollowRedirects();

        return new OkHttpClient().newBuilder()
                .connectionPool(pool)
                .connectTimeout(connectTimeout, connectTimeoutTimeUnit)
                .readTimeout(readTimeout, readTimeoutTimeUnit)
                .writeTimeout(writeTimeout, writeTimeoutTimeUnit)
                .callTimeout(callTimeout, callTimeoutTimeUnit)
                .hostnameVerifier((hostname, sslSession) -> true)
                .followRedirects(followRedirects)
                .sslSocketFactory(sslSocketFactory(), trustManager())
                .build();
    }

    /**
     * 获取SSL套接字
     *
     * @return {@link SSLSocketFactory}
     */
    private SSLSocketFactory sslSocketFactory() {
        SSLSocketFactory sslFactory;
        try {
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(null, new TrustManager[]{trustManager()}, new SecureRandom());
            sslFactory = sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return sslFactory;
    }

    /**
     * 得到信任管理器
     *
     * @return {@link TrustManager[]}
     */
    private static X509TrustManager trustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }
        };
    }


    /**
     * 响应错误处理器
     *
     * @return {@link ResponseErrorHandler}
     */
    private ResponseErrorHandler responseErrorHandler() {
        return new CustomResponseErrorHandler();
    }

    /**
     * http请求拦截器
     *
     * @return {@link List}<{@link ClientHttpRequestInterceptor}>
     */
    private List<ClientHttpRequestInterceptor> httpRequestInterceptorList() {
        // 拦截器集合
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        // 添加接口日志打印拦截器
        interceptors.add(new ApiLogInterceptor());
        return interceptors;
    }

}
