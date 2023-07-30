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
import org.springframework.stereotype.Component;

/**
 * JSON工具类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-03-24 20:49:37
 */
@Slf4j
public class JsonServiceImpl implements JsonService {

    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    interface LogMessage {
        String MSG_01 = "Object convert to JSON Failed.";
        String MSG_02 = "JSON convert to object Failed.";
        String MSG_03 = "JSON convert to JsonNode Failed.";
    }

    @Override
    public String toJson(Object object) {
        if (null == object) {
            return null;
        }
        try {
            return (object instanceof String) ? (String) object : objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.error(LogMessage.MSG_01);
            throw new JsonConvertException(LogMessage.MSG_01, e);
        }
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            log.error(LogMessage.MSG_02);
            throw new JsonConvertException(LogMessage.MSG_02, e);
        }
    }

    @Override
    public <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            log.error(LogMessage.MSG_02);
            throw new JsonConvertException(LogMessage.MSG_02, e);
        }
    }

    @Override
    public JsonNode readTree(String json) {
        try {
            return objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            log.error(LogMessage.MSG_03);
            throw new JsonConvertException(LogMessage.MSG_03, e);
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
