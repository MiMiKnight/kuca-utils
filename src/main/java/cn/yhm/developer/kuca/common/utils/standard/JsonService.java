package cn.yhm.developer.kuca.common.utils.standard;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * JSON工具类接口
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-03-24 20:49:11
 */
public interface JsonService {

    /**
     * 对象转JSON
     *
     * @param object 对象
     * @return {@link String}
     */
    String toJson(Object object);

    /**
     * JSON转对象
     *
     * @param json  JSON字符串
     * @param clazz 对象的Class类型
     * @return {@link T}
     */
    <T> T fromJson(String json, Class<T> clazz);

    /**
     * JSON转对象
     *
     * @param json          JSON字符串
     * @param typeReference 对象类型引用
     * @return {@link T}
     */
    <T> T fromJson(String json, TypeReference<T> typeReference);

    /**
     * 从JSON字符串获取JsonNode
     *
     * @param json JSON字符串
     * @return {@link JsonNode}
     */
    JsonNode readTree(String json);

    /**
     * 从JSON字符串中获取指定子元素的JsonNode对象
     *
     * @param json      JSON字符串
     * @param fieldName 子元素字段名称
     * @return {@link JsonNode}
     */
    JsonNode children(String json, String fieldName);

    /**
     * 从JsonNode对象中获取指定子元素的JsonNode对象
     *
     * @param jsonNode  JsonNode
     * @param fieldName 子元素字段名称
     * @return {@link JsonNode}
     */
    JsonNode children(JsonNode jsonNode, String fieldName);


}
