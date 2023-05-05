package com.yeyeye.ezsender.duplicate.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 配置中心配置
 *
 * @author yeyeye
 * @Date 2023/5/5 20:23
 */
@Data
@Component
@RefreshScope
public class DuplicateRuleConfig {
    @Value("${duplicate.rule1}")
    private String rule1;

    @Value("${duplicate.rule2}")
    private String rule2;
}
