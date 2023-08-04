package com.github.mimiknight.kuca.utils.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mimiknight.kuca.utils.exception.JsonConvertException;
import com.github.mimiknight.kuca.utils.service.standard.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JSON工具类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-03-24 20:49:37
 */
@Slf4j
public class JsonServiceImpl implements JsonService {
    @Autowired
    private ObjectMapper mapper;

    @Override
    public String toJson(Object object) {
        if (null == object) {
            return null;
        }
        try {
            return (object instanceof String) ? (String) object : mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error("Object convert to JSON Failed.");
            throw new JsonConvertException("Object convert to JSON Failed.", e);
        }
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error("JSON convert to object Failed.");
            throw new JsonConvertException("JSON convert to object Failed.", e);
        }
    }

    @Override
    public <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            log.error("JSON convert to object Failed.");
            throw new JsonConvertException("JSON convert to object Failed.", e);
        }
    }

    @Override
    public JsonNode readTree(String json) {
        try {
            return mapper.readTree(json);
        } catch (JsonProcessingException e) {
            log.error("JSON convert to JsonNode Failed.");
            throw new JsonConvertException("JSON convert to JsonNode Failed.", e);
        }
    }


    @Override
    public JsonNode children(JsonNode jsonNode, String fieldName) {
        if (null == jsonNode || StringUtils.isBlank(fieldName)) {
            return null;
        }
        return jsonNode.get(fieldName);
    }

    @Override
    public JsonNode children(String json, String fieldName) {
        JsonNode parent = readTree(json);
        return children(parent, fieldName);
    }

}
