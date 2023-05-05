package com.yeyeye.ezsender.duplicate.rule;

import com.yeyeye.ezsender.duplicate.config.BaseDuplicateRuleConfig;
import com.yeyeye.ezsender.duplicate.config.MultiPushDuplicateConfig;
import com.yeyeye.ezsender.duplicate.config.SimilarDuplicateConfig;
import com.yeyeye.ezsender.duplicate.rule.impl.MultiPushDuplicateRule;
import com.yeyeye.ezsender.duplicate.rule.impl.SimilarDuplicateRule;
import com.yeyeye.ezsender.enums.MessageStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 过滤规则
 *
 * @author yeyeye
 * @Date 2023/4/27 19:44
 */
@Getter
@AllArgsConstructor
public enum DuplicateRuleEnum {
    SIMILAR(100,
            "同一用户 n分钟内 被推送多次同一模板，单位为秒(s)",
            MessageStatus.SIMILAR_DUPLICATE,
            SimilarDuplicateRule.class,
            SimilarDuplicateConfig.class),
    MULTI_PUSH(101,
            "同一用户 n 天内 被推送 m 次消息",
            MessageStatus.MULTI_PUSH_DUPLICATE,
            MultiPushDuplicateRule.class,
            MultiPushDuplicateConfig.class);

    private final Integer code;
    private final String desc;
    /**
     * 消息状态，记录日志用
     */
    private final MessageStatus messageStatus;
    /**
     * 规则处理类
     */
    private final Class<? extends DuplicateRule> rule;
    private final Class<? extends BaseDuplicateRuleConfig> config;

    /**
     * 映射code和类的关系
     */
    private static final Map<Integer, Class<? extends DuplicateRule>> RULE_MAPPING = new HashMap<>();
    /**
     * 映射code和MessageStatus的关系
     */
    private static final Map<Integer, MessageStatus> MSG_STATUS_MAPPING = new HashMap<>();
    /**
     * 映射code和config的关系
     */
    private static final Map<Integer, Class<? extends BaseDuplicateRuleConfig>> CONFIG_MAPPING = new HashMap<>();

    static {
        for (DuplicateRuleEnum ruleEnum : DuplicateRuleEnum.values()) {
            RULE_MAPPING.put(ruleEnum.code, ruleEnum.rule);
            MSG_STATUS_MAPPING.put(ruleEnum.code, ruleEnum.messageStatus);
            CONFIG_MAPPING.put(ruleEnum.code, ruleEnum.config);
        }
    }

    public static Class<? extends DuplicateRule> getClazz(int code) {
        return RULE_MAPPING.get(code);
    }
    public static Class<? extends BaseDuplicateRuleConfig> getConfigClazz(int code) {
        return CONFIG_MAPPING.get(code);
    }

    public static MessageStatus getStatus(int code) {
        return MSG_STATUS_MAPPING.get(code);
    }
}
