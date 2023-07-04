package cn.yhm.developer.kuca.common.utils.impl;

import cn.yhm.developer.kuca.common.utils.standard.HttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

/**
 * HTTP工具实现类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-07 18:04:12
 */
@Component
public class HttpServiceImpl implements HttpService {

    private RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public HttpHeaders emptyHttpHeaders() {
        return new HttpHeaders();
    }

    @Override
    public String uriBuild(String uri, HashMap<String, Object> params) {
        Assert.notNull(uri, "uri can not be null");
        if (null == params || params.size() < 1) {
            return uri;
        }
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(uri);
        params.forEach(uriBuilder::queryParam);
        return uriBuilder.toUriString();
    }

    @Override
    public ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method, HttpHeaders headers) {
        String uri = apiDomain + apiPath;
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(uri, method, httpEntity, String.class);
    }

    @Override
    public <T> ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method, HttpHeaders headers,
                                             T body) {
        String uri = apiDomain + apiPath;
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<T> httpEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(uri, method, httpEntity, String.class);
    }

    @Override
    public ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method,
                                         HttpHeaders headers, Object... pathVariable) {
        String uri = apiDomain + apiPath;
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(uri, method, httpEntity, String.class, pathVariable);
    }

    @Override
    public <T> ResponseEntity<String> doHttp(String apiDomain, String apiPath, HttpMethod method,
                                             HttpHeaders headers, T body, Object... pathVariable) {
        String uri = apiDomain + apiPath;
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<T> httpEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(uri, method, httpEntity, String.class, pathVariable);
    }

    @Override
    public ResponseEntity<String> doGet(String apiDomain, String apiPath, HttpHeaders headers) {
        return doHttp(apiDomain, apiPath, HttpMethod.GET, headers);
    }

    @Override
    public ResponseEntity<String> doGet(String apiDomain, String apiPath, HttpHeaders headers,
                                        HashMap<String, Object> params) {
        String uri = apiDomain + apiPath;
        uri = uriBuild(uri, params);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
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
