package com.yeyeye.ezsender.receiver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yeyeye.ezsender.enums.MQConstant;
import com.yeyeye.ezsender.handler.Handler;
import com.yeyeye.ezsender.pojo.TaskInfo;
import jakarta.annotation.Resource;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 接受MQ内的消息
 *
 * @author yeyeye
 * @Date 2023/4/11 22:36
 */
@Component
public class MessageReceiver {
    @Autowired
    private TaskDispatcher taskDispatcher;

    @Resource
    private Map<Integer, Handler> handlerMap;

    @RabbitListener(queues = MQConstant.QUEUE_NAME)
    public void receiver(List<TaskInfo> taskInfos) {
        //获取任务类型
        List<Task> tasks = new ArrayList<>();
        for (TaskInfo taskInfo : taskInfos) {
            tasks.add(new Task(taskInfo, handlerMap));
        }
        taskDispatcher.dispatch(taskInfos.get(0).getTaskType(), tasks);
    }
}
