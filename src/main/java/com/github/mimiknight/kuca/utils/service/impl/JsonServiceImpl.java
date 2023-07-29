package com.github.mimiknight.kuca.utils.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.mimiknight.kuca.utils.constant.DateTimeFormatStandard;
import com.github.mimiknight.kuca.utils.exception.JsonConvertException;
import com.github.mimiknight.kuca.utils.service.standard.JsonService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

/**
 * JSON工具类
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-03-24 20:49:37
 */
@Slf4j
@Component
public class JsonServiceImpl implements JsonService {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    static {
        //对象的所有字段全部列入
        MAPPER.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //取消默认转换timestamps形式
        MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        //忽略空Bean转json的错误
        MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //所有的日期格式都统一为以下的样式，即yyyy-MM-dd HH:mm:ss.SSS XXX
        MAPPER.setDateFormat(new SimpleDateFormat(DateTimeFormatStandard.STANDARD_4));
        //忽略 在json字符串中存在，但是在java对象中不存在对应属性的情况。防止错误
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // 注册java8新时间类型模块
        MAPPER.registerModule(new JavaTimeModule());
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
            return (object instanceof String) ? (String) object : MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            log.error(LogMessage.MSG_01);
            throw new JsonConvertException(LogMessage.MSG_01, e);
        }
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            log.error(LogMessage.MSG_02);
            throw new JsonConvertException(LogMessage.MSG_02, e);
        }
    }

    @Override
    public <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return MAPPER.readValue(json, typeReference);
        } catch (Exception e) {
            log.error(LogMessage.MSG_02);
            throw new JsonConvertException(LogMessage.MSG_02, e);
        }
    }

    @Override
    public JsonNode readTree(String json) {
        try {
            return MAPPER.readTree(json);
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
