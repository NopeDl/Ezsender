package com.yeyeye.ezsender.duplicate.config;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 同一用户 一天内 被推送多次消息
 * 例子:{"limit":"86400","rule":101 ,"num":5}
 *
 * @author yeyeye
 * @Date 2023/5/5 14:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MultiPushDuplicateConfig extends BaseDuplicateRuleConfig{
    private int num;
}
