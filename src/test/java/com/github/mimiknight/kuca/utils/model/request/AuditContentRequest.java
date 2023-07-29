package com.github.mimiknight.kuca.utils.model.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author victor2015yhm@gmail.com
 * @since 2023-03-09 20:26:22
 */
@Getter
@Setter
public class AuditContentRequest {

    /**
     * 审核结果
     * <p>
     * 1：审核通过
     * <p>
     * 2：审核不通过
     */
    private Integer auditResult;

    /**
     * 审核时间
     * <p>
     * 24小时制 "年-月-日 时:分:秒.毫秒 GMT时区"
     * 格式：
     * <p>
     * 2022-09-04 10:06:39.123 GMT+08:00 【表示 东八区 2022年9月4日10点6分39秒123毫秒】
     */
    private String auditTime;
}
