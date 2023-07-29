package com.github.mimiknight.kuca.utils.service.impl;

import com.github.mimiknight.kuca.utils.service.standard.HttpService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {HttpServiceImpl.class,
                RestTemplate.class,
                JsonServiceImpl.class})
public class HttpServiceImplTest {

    private HttpService httpService;

    @Autowired
    public void setHttpService(HttpService httpService) {
        this.httpService = httpService;
    }

    @Test
    void doGet() {

    }
}