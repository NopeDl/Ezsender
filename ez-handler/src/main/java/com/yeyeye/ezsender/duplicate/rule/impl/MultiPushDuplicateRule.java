package com.yeyeye.ezsender.duplicate.rule.impl;

import com.yeyeye.ezsender.duplicate.config.MultiPushDuplicateConfig;
import com.yeyeye.ezsender.duplicate.rule.DuplicateRuleEnum;
import com.yeyeye.ezsender.pojo.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * 同一用户 一天内 被推送多次消息
 * 例子：{"limit":"86400","rule":101 ,"num":5}
 *
 * @author yeyeye
 * @Date 2023/4/27 19:50
 */
@Component
public class MultiPushDuplicateRule extends BaseDuplicateRule {
    private int num;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    /**
     * 业务前缀
     */
    private static final String MULTI_PUSH_PREFIX = "rule:" + DuplicateRuleEnum.MULTI_PUSH.getCode() + ":";

    @Override
    protected void duplication(TaskInfo taskInfo) {
        Iterator<String> receivers = taskInfo.getReceiver().iterator();
        while (receivers.hasNext()) {
            //去重前缀 + 接受者
            String key = MULTI_PUSH_PREFIX + receivers.next();
            //为这个key设置一个超时时间
            redisTemplate.opsForValue().setIfAbsent(key, String.valueOf(0), limit, TimeUnit.SECONDS);
            Long increment = redisTemplate.opsForValue().increment(key);
            if (increment != null && increment > num) {
                receivers.remove();
            }
        }
    }

    public void setConfig(MultiPushDuplicateConfig config) {
        this.rule = config.getRule();
        this.limit = config.getRule();
        this.num = config.getNum();
    }
}

