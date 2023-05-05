package com.yeyeye.ezsender.duplicate.rule.impl;

import com.yeyeye.ezsender.duplicate.config.SimilarDuplicateConfig;
import com.yeyeye.ezsender.duplicate.rule.DuplicateRuleEnum;
import com.yeyeye.ezsender.pojo.TaskInfo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;

/**
 * 同一用户 时间n内 被推送多次同一模板，单位为s
 *
 * @author yeyeye
 * @Date 2023/4/27 19:49
 */
@Component
@Setter
public class SimilarDuplicateRule extends BaseDuplicateRule {
    private static final String SIMILAR_DUPLICATE_PREFIX = "rule:" + DuplicateRuleEnum.SIMILAR.getCode() + ":";
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void duplication(TaskInfo taskInfo) {
        Iterator<String> receivers = taskInfo.getReceiver().iterator();
        while (receivers.hasNext()) {
            Long increment = redisTemplate.opsForValue().increment(SIMILAR_DUPLICATE_PREFIX + receivers.next());
            if (increment != null && increment > limit) {
                //说明达到限制了，要取消掉本次发送
                receivers.remove();
            }
        }
    }

    public void setConfig(SimilarDuplicateConfig config) {
        this.rule = config.getRule();
        this.limit = config.getLimit();
    }
}
