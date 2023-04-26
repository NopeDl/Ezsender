package com.yeyeye.ezsender.duplicate.service;

import com.yeyeye.ezsender.pojo.TaskInfo;

import java.util.List;

/**
 * 去重服务
 *
 * @author yeyeye
 * @Date 2023/4/26 22:30
 */
public interface DuplicateService {
    /**
     * 对任务去重
     *
     * @param taskInfos 一堆任务
     */
    void duplicateBatchTask(List<TaskInfo> taskInfos);
}
