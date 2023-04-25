package com.yeyeye.ezsender.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yeyeye.ezsender.enums.MQConstant;
import com.yeyeye.ezsender.pojo.TaskInfo;
import com.yeyeye.ezsender.receiver.Task;
import com.yeyeye.ezsender.receiver.TaskDispatcher;
import com.yeyeye.ezsender.service.ConsumeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void consumeToSend(List<TaskInfo> taskInfos) {
        //包装成任务类型
        List<TaskInfo> retTaskInfos = new ArrayList<>();
        for (TaskInfo taskInfo : taskInfos) {
            Task task = new Task(taskInfo, taskDispatcher.getTaskHandlerMap());
            boolean dispatchSuccess = taskDispatcher.dispatch(task);
            //转发失败，将消息封装后重新加入消息队列
            if (!dispatchSuccess) {
                retTaskInfos.add(taskInfo);
            }
        }
        //如果存在转发失败的消息，将其重新入队
        if (retTaskInfos.size() > 0) {
            String retMsg = JSON.toJSONString(retTaskInfos, SerializerFeature.WriteClassName);
            rabbitTemplate.convertAndSend(MQConstant.EXCHANGE_NAME,
                    MQConstant.ROUTING_KEY,
                    retMsg);
        }
    }
}
