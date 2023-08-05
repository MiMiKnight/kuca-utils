package com.github.mimiknight.kuca.utils.service.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.mimiknight.kuca.utils.service.standard.JsonService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * JSON工具类
 *
 * @author MiMiKnight victor2015yhm@gmail.com
 * @since 2023-08-04 19:39:36
 */
public class JsonUtils {
    @Autowired
    private JsonService jsonService;

    private static JsonUtils instance;

    private JsonUtils() {
    }

    @PostConstruct
    public void init() {
        instance = this;
        instance.jsonService = this.jsonService;
    }

    public static String toJson(Object object) {
        return instance.jsonService.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return instance.jsonService.fromJson(json, clazz);
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        return instance.jsonService.fromJson(json, typeReference);
    }

    public static JsonNode readTree(String json) {
        return instance.jsonService.readTree(json);
    }

    public JsonNode children(JsonNode jsonNode, String fieldName) {
        return instance.jsonService.children(jsonNode, fieldName);
    }

    public static JsonNode children(String json, String fieldName) {
        return instance.jsonService.children(json, fieldName);
    }

    public static ObjectNode createObjectNode() {
        return instance.jsonService.createObjectNode();
    }

}
