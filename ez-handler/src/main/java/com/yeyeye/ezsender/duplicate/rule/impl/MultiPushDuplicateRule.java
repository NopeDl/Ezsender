package com.yeyeye.ezsender.duplicate.rule.impl;

import com.yeyeye.ezsender.duplicate.config.MultiPushDuplicateConfig;
import com.yeyeye.ezsender.pojo.TaskInfo;
import org.springframework.stereotype.Component;

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

    @Override
    protected void duplication(TaskInfo taskInfo) {

    }

    public void setConfig(MultiPushDuplicateConfig config) {
        this.rule = config.getRule();
        this.limit = config.getRule();
        this.num = config.getNum();
    }
}

