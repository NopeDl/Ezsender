package com.yeyeye.ezsender.duplicate.rule.impl;

import com.alibaba.fastjson.JSON;
import com.yeyeye.ezsender.duplicate.rule.DuplicateRule;
import com.yeyeye.ezsender.duplicate.rule.DuplicateRuleEnum;
import com.yeyeye.ezsender.pojo.TaskInfo;
import com.yeyeye.ezsender.utils.LogUtil;

/**
 * @author yeyeye
 * @Date 2023/5/2 15:00
 */
public abstract class BaseDuplicateRule implements DuplicateRule {
    protected int rule;
    protected int limit;

    @Override
    public void duplicate(TaskInfo taskInfo) {
        //执行去重
        duplication(taskInfo);
        //记录日志
        LogUtil.info(DuplicateRuleEnum.getStatus(rule), JSON.toJSONString(taskInfo));
    }

    protected abstract void duplication(TaskInfo taskInfo);
}
