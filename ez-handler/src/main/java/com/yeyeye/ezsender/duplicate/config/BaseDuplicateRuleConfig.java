package com.yeyeye.ezsender.duplicate.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yeyeye
 * @Date 2023/5/2 17:11
 */
@Data
public abstract class BaseDuplicateRuleConfig {
    private int rule;
    private int limit;
}
