package com.yeyeye.ezsender.service;

import com.yeyeye.ezsender.pojo.TaskInfo;

import java.util.List;

/**
 * @author yeyeye
 * @Date 2023/4/24 0:14
 */
public interface ConsumeService {
    void consumeToSend(List<TaskInfo> taskInfos);
}
