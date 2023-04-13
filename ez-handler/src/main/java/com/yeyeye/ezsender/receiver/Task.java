package com.yeyeye.ezsender.receiver;

import com.yeyeye.ezsender.handler.Handler;
import com.yeyeye.ezsender.pojo.TaskInfo;
import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * @author yeyeye
 * @Date 2023/4/13 17:12
 */
@AllArgsConstructor
public class Task implements Runnable {
    private TaskInfo taskInfo;
    private Map<Integer, Handler> handlerMap;

    @Override
    public void run() {
        handlerMap.get(taskInfo.getTaskType()).handle(taskInfo);
    }
}
