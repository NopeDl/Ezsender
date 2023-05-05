package com.yeyeye.ezsender.receiver;

import com.yeyeye.ezsender.duplicate.service.DuplicateService;
import com.yeyeye.ezsender.handler.Handler;
import com.yeyeye.ezsender.pojo.TaskInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/**
 * @author yeyeye
 * @Date 2023/4/13 17:12
 */
@Getter
@AllArgsConstructor
public class Task implements Runnable {
    private TaskInfo taskInfo;
    private Map<Integer, Handler> handlerMap;
    private DuplicateService duplicateService;

    @Override
    public void run() {
        //去重
        duplicateService.duplicateTask(taskInfo);
        //调第三方API
        if (taskInfo.getReceiver().size() > 0) {
            handlerMap.get(taskInfo.getTaskType()).handle(taskInfo);
        }
    }
}
