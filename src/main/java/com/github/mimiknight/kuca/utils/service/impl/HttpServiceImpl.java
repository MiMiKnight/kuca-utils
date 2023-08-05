package com.github.mimiknight.kuca.utils.service.impl;

import com.github.mimiknight.kuca.utils.service.standard.HttpService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * HTTP工具实现类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-07 18:04:12
 */
public class HttpServiceImpl implements HttpService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public HttpHeaders emptyHttpHeaders() {
        return new HttpHeaders();
    }

    @Override
    public HttpHeaders jsonHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    @Override
    public String uriBuild(String uri, Map<String, Object> params) {
        Assert.notNull(uri, "uri can not be null");
        if (MapUtils.isEmpty(params)) {
            return uri;
        }
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(uri);
        params.forEach(uriBuilder::queryParam);
        return uriBuilder.toUriString();
    }

    @Override
    public ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method, HttpHeaders headers) {
        String uri = apiDomain + apiPath;
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(uri, method, httpEntity, String.class);
    }

    @Override
    public <T> ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method, HttpHeaders headers,
                                             T body) {
        String uri = apiDomain + apiPath;
        HttpEntity<T> httpEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(uri, method, httpEntity, String.class);
    }

    @Override
    public ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method,
                                         HttpHeaders headers, Object... pathVariable) {
        String uri = apiDomain + apiPath;
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(uri, method, httpEntity, String.class, pathVariable);
    }

    @Override
    public <T> ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method,
                                             HttpHeaders headers, T body, Object... pathVariable) {
        String uri = apiDomain + apiPath;
        HttpEntity<T> httpEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(uri, method, httpEntity, String.class, pathVariable);
    }

    @Override
    public ResponseEntity<String> doGet(String apiDomain, String apiPath, HttpHeaders headers) {
        return doHttp(apiDomain, apiPath, HttpMethod.GET, headers);
    }

    @Override
    public ResponseEntity<String> doGet(String apiDomain, String apiPath, HttpHeaders headers,
                                        Map<String, Object> params) {
        String uri = apiDomain + apiPath;
        uri = uriBuild(uri, params);
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class);
    }

    @Override
    public ResponseEntity<String> doGet(String apiDomain, String apiPath, HttpHeaders headers,
                                        Object... pathVariable) {
        return doHttp(apiDomain, apiPath, HttpMethod.GET, headers, String.class, pathVariable);
    }

    @Override
    public <T> ResponseEntity<String> doPost(String apiDomain, String apiPath, HttpHeaders headers, T body) {
        return doHttp(apiDomain, apiPath, HttpMethod.POST, headers, body);
    }

    @Override
    public <T> ResponseEntity<String> doPost(String apiDomain, String apiPath, HttpHeaders headers, T body,
                                             Object... pathVariable) {
        return doHttp(apiDomain, apiPath, HttpMethod.POST, headers, body, pathVariable);
    }

    @Override
    public <T> ResponseEntity<String> doPut(String apiDomain, String apiPath, HttpHeaders headers, T body) {
        return doHttp(apiDomain, apiPath, HttpMethod.PUT, headers, body);
    }

    @Override
    public <T> ResponseEntity<String> doPut(String apiDomain, String apiPath, HttpHeaders headers, T body,
                                            Object... pathVariable) {
        return doHttp(apiDomain, apiPath, HttpMethod.PUT, headers, body, pathVariable);
    }

    @Override
    public <T> ResponseEntity<String> doDelete(String apiDomain, String apiPath, HttpHeaders headers, T body) {
        return doHttp(apiDomain, apiPath, HttpMethod.DELETE, headers, body);
    }

    @Override
    public <T> ResponseEntity<String> doDelete(String apiDomain, String apiPath, HttpHeaders headers, T body,
                                               Object... pathVariable) {
        return doHttp(apiDomain, apiPath, HttpMethod.DELETE, headers, body, pathVariable);
    }

    @Override
    public <T> ResponseEntity<String> doPatch(String apiDomain, String apiPath, HttpHeaders headers, T body) {
        return doHttp(apiDomain, apiPath, HttpMethod.PATCH, headers, body);
    }

    @Override
    public <T> ResponseEntity<String> doPatch(String apiDomain, String apiPath, HttpHeaders headers, T body,
                                              Object... pathVariable) {
        return doHttp(apiDomain, apiPath, HttpMethod.PATCH, headers, body, pathVariable);
    }

    @Override
    public <T> ResponseEntity<String> doOptions(String apiDomain, String apiPath, HttpHeaders headers, T body) {
        return doHttp(apiDomain, apiPath, HttpMethod.OPTIONS, headers, body);
    }

    @Override
    public <T> ResponseEntity<String> doOptions(String apiDomain, String apiPath, HttpHeaders headers, T body,
                                                Object... pathVariable) {
        return doHttp(apiDomain, apiPath, HttpMethod.OPTIONS, headers, body, pathVariable);
    }

    @Override
    public <T> ResponseEntity<String> doHead(String apiDomain, String apiPath, HttpHeaders headers, T body) {
        return doHttp(apiDomain, apiPath, HttpMethod.HEAD, headers, body);
    }

    @Override
    public <T> ResponseEntity<String> doHead(String apiDomain, String apiPath, HttpHeaders headers, T body,
                                             Object... pathVariable) {
        return doHttp(apiDomain, apiPath, HttpMethod.HEAD, headers, body, pathVariable);
    }

    @Override
    public <T> ResponseEntity<String> doTrace(String apiDomain, String apiPath, HttpHeaders headers, T body) {
        return doHttp(apiDomain, apiPath, HttpMethod.TRACE, headers, body);
    }

    @Override
    public <T> ResponseEntity<String> doTrace(String apiDomain, String apiPath, HttpHeaders headers, T body,
                                              Object... pathVariable) {
        return doHttp(apiDomain, apiPath, HttpMethod.TRACE, headers, body, pathVariable);
    }

}
