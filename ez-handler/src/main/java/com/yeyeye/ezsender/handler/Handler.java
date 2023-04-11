package com.yeyeye.ezsender.handler;

import com.yeyeye.ezsender.pojo.TaskInfo;

/**
 * @author yeyeye
 * @Date 2023/4/4 19:23
 */
public interface Handler {
    /**
     * 执行处理
     */
    void handle(TaskInfo taskInfo);
}
