package com.github.mimiknight.kuca.utils.service.utils;

import com.github.mimiknight.kuca.utils.service.standard.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * HTTP工具类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-08-04 20:15:45
 */
public class HttpUtils {

    @Autowired
    private HttpService httpService;

    private static HttpUtils instance;

    private HttpUtils() {
    }

    @PostConstruct
    public void init() {
        instance = this;
        instance.httpService = this.httpService;
    }

    public static HttpHeaders emptyHttpHeaders() {
        return instance.httpService.emptyHttpHeaders();
    }

    public static HttpHeaders jsonHttpHeaders() {
        return instance.httpService.jsonHttpHeaders();
    }

    public static String uriBuild(String uri, Map<String, Object> params) {
        return instance.httpService.uriBuild(uri, params);
    }

    public static ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method, HttpHeaders headers) {
        return instance.httpService.doHttp(apiDomain, apiPath, method, headers);
    }

    public static <T> ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method, HttpHeaders headers, T body) {
        return instance.httpService.doHttp(apiDomain, apiPath, method, headers, body);
    }

    public static ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method, HttpHeaders headers, Object... pathVariable) {
        return instance.httpService.doHttp(apiDomain, apiPath, method, headers, pathVariable);
    }

    public static <T> ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method, HttpHeaders headers, T body, Object... pathVariable) {
        return instance.httpService.doHttp(apiDomain, apiPath, method, headers, body, pathVariable);
    }

    public static ResponseEntity<String> doGet(String apiDomain, String apiPath, HttpHeaders headers) {
        return instance.httpService.doGet(apiDomain, apiPath, headers);
    }

    public static ResponseEntity<String> doGet(String apiDomain, String apiPath, HttpHeaders headers, Map<String, Object> params) {
        return instance.httpService.doGet(apiDomain, apiPath, headers, params);
    }

    public static ResponseEntity<String> doGet(String apiDomain, String apiPath, HttpHeaders headers, Object... pathVariable) {
        return instance.httpService.doGet(apiDomain, apiPath, headers, pathVariable);
    }

    public static <T> ResponseEntity<String> doPost(String apiDomain, String apiPath, HttpHeaders headers, T body) {
        return instance.httpService.doPost(apiDomain, apiPath, headers, body);
    }

    public static <T> ResponseEntity<String> doPost(String apiDomain, String apiPath, HttpHeaders headers, T body, Object... pathVariable) {
        return instance.httpService.doPost(apiDomain, apiPath, headers, body, pathVariable);
    }

    public static <T> ResponseEntity<String> doPut(String apiDomain, String apiPath, HttpHeaders headers, T body) {
        return instance.httpService.doPut(apiDomain, apiPath, headers, body);
    }

    public static <T> ResponseEntity<String> doPut(String apiDomain, String apiPath, HttpHeaders headers, T body, Object... pathVariable) {
        return instance.httpService.doPut(apiDomain, apiPath, headers, body, pathVariable);
    }

    public static <T> ResponseEntity<String> doDelete(String apiDomain, String apiPath, HttpHeaders headers, T body) {
        return instance.httpService.doDelete(apiDomain, apiPath, headers, body);
    }

    public static <T> ResponseEntity<String> doDelete(String apiDomain, String apiPath, HttpHeaders headers, T body, Object... pathVariable) {
        return instance.httpService.doDelete(apiDomain, apiPath, headers, body, pathVariable);
    }

    public static <T> ResponseEntity<String> doPatch(String apiDomain, String apiPath, HttpHeaders headers, T body) {
        return instance.httpService.doPatch(apiDomain, apiPath, headers, body);
    }

    public static <T> ResponseEntity<String> doPatch(String apiDomain, String apiPath, HttpHeaders headers, T body, Object... pathVariable) {
        return instance.httpService.doPatch(apiDomain, apiPath, headers, body, pathVariable);
    }

    public static <T> ResponseEntity<String> doOptions(String apiDomain, String apiPath, HttpHeaders headers, T body) {
        return instance.httpService.doOptions(apiDomain, apiPath, headers, body);
    }

    public static <T> ResponseEntity<String> doOptions(String apiDomain, String apiPath, HttpHeaders headers, T body, Object... pathVariable) {
        return instance.httpService.doOptions(apiDomain, apiPath, headers, body, pathVariable);
    }


    public static <T> ResponseEntity<String> doHead(String apiDomain, String apiPath, HttpHeaders headers, T body) {
        return instance.httpService.doHead(apiDomain, apiPath, headers, body);
    }


    public static <T> ResponseEntity<String> doHead(String apiDomain, String apiPath, HttpHeaders headers, T body, Object... pathVariable) {
        return instance.httpService.doHead(apiDomain, apiPath, headers, body, pathVariable);
    }


    public static <T> ResponseEntity<String> doTrace(String apiDomain, String apiPath, HttpHeaders headers, T body) {
        return instance.httpService.doTrace(apiDomain, apiPath, headers, body);
    }


    public static <T> ResponseEntity<String> doTrace(String apiDomain, String apiPath, HttpHeaders headers, T body, Object... pathVariable) {
        return instance.httpService.doTrace(apiDomain, apiPath, headers, body, pathVariable);
    }
}
