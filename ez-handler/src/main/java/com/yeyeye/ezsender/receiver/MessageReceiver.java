package com.yeyeye.ezsender.receiver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yeyeye.ezsender.enums.MQConstant;
import com.yeyeye.ezsender.pojo.TaskInfo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    @RabbitListener(queues = MQConstant.QUEUE_NAME)
    public void receiver(String msg) {
        System.out.println(msg);
        List<TaskInfo> taskInfos = JSON.parseArray(msg, TaskInfo.class);
        //获取任务类型
        List<Task> tasks = new ArrayList<>();
        for (TaskInfo taskInfo : taskInfos) {
            tasks.add(new Task(taskInfo, taskDispatcher.getTaskHandlerMap()));
        }
        taskDispatcher.dispatch(taskInfos.get(0).getTaskType(), tasks);
    }
}
