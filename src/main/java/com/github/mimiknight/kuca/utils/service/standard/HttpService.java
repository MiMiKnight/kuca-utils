package com.github.mimiknight.kuca.utils.service.standard;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

/**
 * HTTP工具类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-07 18:02:17
 */
public interface HttpService {

    /**
     * 空HTTP头
     * <p>
     * Content-Type = application/json
     *
     * @return {@link HttpHeaders}
     */
    HttpHeaders emptyHttpHeaders();


    /**
     * uri构建
     * 拼接请求行参数到请求路径中
     *
     * @param uri    统一资源标识符
     * @param params 请求行参数
     * @return {@link String}
     */
    String uriBuild(String uri, HashMap<String, Object> params);


    /**
     * 执行HTTP
     *
     * @param apiDomain 接口域名
     * @param apiPath   接口路径
     * @param method    请求方式
     * @param headers   请求头
     * @return {@link ResponseEntity}<{@link String}>
     */
    ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method, HttpHeaders headers);


    /**
     * 执行HTTP
     *
     * @param apiDomain 接口域名
     * @param apiPath   接口路径
     * @param method    请求方式
     * @param headers   请求头
     * @param body      请求体
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method, HttpHeaders headers,
                                      T body);

    /**
     * 执行HTTP
     *
     * @param apiDomain    接口域名
     * @param apiPath      接口路径
     * @param method       请求方式
     * @param headers      请求头
     * @param pathVariable 路径变量
     * @return {@link ResponseEntity}<{@link String}>
     */
    ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method, HttpHeaders headers,
                                  Object... pathVariable);

    /**
     * 执行HTTP
     *
     * @param apiDomain    接口域名
     * @param apiPath      接口路径
     * @param method       请求方式
     * @param headers      请求头
     * @param body         请求体
     * @param pathVariable 路径变量
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method, HttpHeaders headers,
                                      T body, Object... pathVariable);


    /**
     * 执行Get请求
     *
     * @param apiDomain 接口域名
     * @param apiPath   接口路径
     * @param headers   请求头
     * @return {@link ResponseEntity}<{@link String}>
     */
    ResponseEntity<String> doGet(String apiDomain, String apiPath, HttpHeaders headers);


    /**
     * 执行Get请求
     *
     * @param apiDomain 接口域名
     * @param apiPath   接口路径
     * @param headers   请求头
     * @param params    url参数
     * @return {@link ResponseEntity}<{@link String}>
     */
    ResponseEntity<String> doGet(String apiDomain, String apiPath, HttpHeaders headers,
                                 HashMap<String, Object> params);

    /**
     * 执行Get请求
     *
     * @param apiDomain    接口域名
     * @param apiPath      接口路径
     * @param headers      请求头
     * @param pathVariable 路径变量
     * @return {@link ResponseEntity}<{@link String}>
     */
    ResponseEntity<String> doGet(String apiDomain, String apiPath, HttpHeaders headers, Object... pathVariable);

    /**
     * 执行Post请求
     *
     * @param apiDomain 接口域名
     * @param apiPath   接口路径
     * @param headers   请求头
     * @param body      请求体
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doPost(String apiDomain, String apiPath, HttpHeaders headers, T body);

    /**
     * 执行Post请求
     *
     * @param apiDomain    接口域名
     * @param apiPath      接口路径
     * @param headers      请求头
     * @param body         请求体
     * @param pathVariable 路径变量
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doPost(String apiDomain, String apiPath, HttpHeaders headers, T body,
                                      Object... pathVariable);

    /**
     * 执行Put请求
     *
     * @param apiDomain 接口域名
     * @param apiPath   接口路径
     * @param headers   请求头
     * @param body      请求体
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doPut(String apiDomain, String apiPath, HttpHeaders headers, T body);

    /**
     * 执行Put请求
     *
     * @param apiDomain    接口域名
     * @param apiPath      接口路径
     * @param headers      请求头
     * @param body         请求体
     * @param pathVariable 路径变量
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doPut(String apiDomain, String apiPath, HttpHeaders headers, T body,
                                     Object... pathVariable);

    /**
     * 执行Delete请求
     *
     * @param apiDomain 接口域名
     * @param apiPath   接口路径
     * @param headers   请求头
     * @param body      请求体
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doDelete(String apiDomain, String apiPath, HttpHeaders headers, T body);

    /**
     * 执行Delete请求
     *
     * @param apiDomain    接口域名
     * @param apiPath      接口路径
     * @param headers      请求头
     * @param body         请求体
     * @param pathVariable 路径变量
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doDelete(String apiDomain, String apiPath, HttpHeaders headers, T body,
                                        Object... pathVariable);

    /**
     * 执行Patch请求
     *
     * @param apiDomain 接口域名
     * @param apiPath   接口路径
     * @param headers   请求头
     * @param body      请求体
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doPatch(String apiDomain, String apiPath, HttpHeaders headers, T body);

    /**
     * 执行Patch请求
     *
     * @param apiDomain    接口域名
     * @param apiPath      接口路径
     * @param headers      请求头
     * @param body         请求体
     * @param pathVariable 路径变量
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doPatch(String apiDomain, String apiPath, HttpHeaders headers, T body,
                                       Object... pathVariable);

    /**
     * 执行Options请求
     *
     * @param apiDomain 接口域名
     * @param apiPath   接口路径
     * @param headers   请求头
     * @param body      请求体
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doOptions(String apiDomain, String apiPath, HttpHeaders headers, T body);

    /**
     * 执行Options请求
     *
     * @param apiDomain    接口域名
     * @param apiPath      接口路径
     * @param headers      请求头
     * @param body         请求体
     * @param pathVariable 路径变量
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doOptions(String apiDomain, String apiPath, HttpHeaders headers, T body,
                                         Object... pathVariable);

    /**
     * 执行Head请求
     *
     * @param apiDomain 接口域名
     * @param apiPath   接口路径
     * @param headers   请求头
     * @param body      请求体
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doHead(String apiDomain, String apiPath, HttpHeaders headers, T body);

    /**
     * 执行Head请求
     *
     * @param apiDomain    接口域名
     * @param apiPath      接口路径
     * @param headers      请求头
     * @param body         请求体
     * @param pathVariable 路径变量
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doHead(String apiDomain, String apiPath, HttpHeaders headers, T body,
                                      Object... pathVariable);

    /**
     * 执行Trace请求
     *
     * @param apiDomain 接口域名
     * @param apiPath   接口路径
     * @param headers   请求头
     * @param body      请求体
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doTrace(String apiDomain, String apiPath, HttpHeaders headers, T body);

    /**
     * 执行Trace请求
     *
     * @param apiDomain    接口域名
     * @param apiPath      接口路径
     * @param headers      请求头
     * @param body         请求体
     * @param pathVariable 路径变量
     * @return {@link ResponseEntity}<{@link String}>
     */
    <T> ResponseEntity<String> doTrace(String apiDomain, String apiPath, HttpHeaders headers, T body,
                                       Object... pathVariable);
}
