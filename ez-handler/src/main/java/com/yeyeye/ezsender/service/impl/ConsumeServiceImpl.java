package com.yeyeye.ezsender.service.impl;

import com.yeyeye.ezsender.pojo.TaskInfo;
import com.yeyeye.ezsender.receiver.Task;
import com.yeyeye.ezsender.receiver.TaskDispatcher;
import com.yeyeye.ezsender.service.ConsumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 消费消息
 *
 * @author yeyeye
 * @Date 2023/4/24 0:15
 */
@Component
public class ConsumeServiceImpl implements ConsumeService {
    @Autowired
    private TaskDispatcher taskDispatcher;

    @Override
    public void consumeToSend(List<TaskInfo> taskInfos) {
        //去重
        //包装成任务类型并转发任务
        for (TaskInfo taskInfo : taskInfos) {
            Task task = new Task(taskInfo, taskDispatcher.getTaskHandlerMap());
            taskDispatcher.dispatch(task);
        }
    }
}
