package com.github.mimiknight.kuca.common.component.sensitive.strategy;

/**
 * 敏感策略接口
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-22 01:57:52
 */
public interface SensitiveStrategy {

    /**
     * 脱敏方法
     *
     * @param text 敏感文本
     * @return {@link String}
     */
    String desensitize(String text);
}
