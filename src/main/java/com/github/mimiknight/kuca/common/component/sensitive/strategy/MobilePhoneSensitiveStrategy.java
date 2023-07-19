package com.github.mimiknight.kuca.common.component.sensitive.strategy;

/**
 * 中国大陆手机号敏感策略
 *
 * @author victor2015yhm@gmail.com
 * @since 2023-05-22 01:59:06
 */
public class MobilePhoneSensitiveStrategy implements SensitiveStrategy {
    @Override
    public String desensitize(String text) {
        return null;
    }
}
