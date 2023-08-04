package com.github.mimiknight.kuca.utils.service.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
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

    /**
     * 对象转JSON
     *
     * @param object 对象
     * @return {@link String}
     */
    public static String toJson(Object object) {
        return instance.jsonService.toJson(object);
    }

    /**
     * JSON转对象
     *
     * @param json  JSON字符串
     * @param clazz 对象的Class类型
     * @return {@link T}
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return instance.jsonService.fromJson(json, clazz);
    }

    /**
     * JSON转对象
     *
     * @param json          JSON字符串
     * @param typeReference 对象类型引用
     * @return {@link T}
     */
    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        return instance.jsonService.fromJson(json, typeReference);
    }

    /**
     * 从JSON字符串获取JsonNode
     *
     * @param json JSON字符串
     * @return {@link JsonNode}
     */
    public static JsonNode readTree(String json) {
        return instance.jsonService.readTree(json);
    }

    /**
     * 从JsonNode对象中获取指定子元素的JsonNode对象
     *
     * @param jsonNode  JsonNode
     * @param fieldName 子元素字段名称
     * @return {@link JsonNode}
     */
    public JsonNode children(JsonNode jsonNode, String fieldName) {
        return instance.jsonService.children(jsonNode, fieldName);
    }

    /**
     * 从JSON字符串中获取指定子元素的JsonNode对象
     *
     * @param json      JSON字符串
     * @param fieldName 子元素字段名称
     * @return {@link JsonNode}
     */
    public static JsonNode children(String json, String fieldName) {
        return instance.jsonService.children(json, fieldName);
    }

}
