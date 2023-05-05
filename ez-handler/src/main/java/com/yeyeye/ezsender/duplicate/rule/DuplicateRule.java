package com.yeyeye.ezsender.duplicate.rule;

import com.yeyeye.ezsender.pojo.TaskInfo;

/**
 * @author yeyeye
 * @Date 2023/4/27 19:43
 */
public interface DuplicateRule {
    void duplicate(TaskInfo taskInfo);
}
