package com.yeyeye.ezsender.receiver;

import com.alibaba.fastjson.JSON;
import com.yeyeye.ezsender.enums.MQConstant;
import com.yeyeye.ezsender.pojo.TaskInfo;
import com.yeyeye.ezsender.service.ConsumeService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 接受MQ内的消息并调用服务处理这些消息
 *
 * @author yeyeye
 * @Date 2023/4/11 22:36
 */
@Component
public class MessageReceiver {
    @Autowired
    private ConsumeService sendService;

    @RabbitListener(queues = MQConstant.QUEUE_NAME)
    public void receiver(String msg) {
        List<TaskInfo> taskInfos = JSON.parseArray(msg, TaskInfo.class);
        sendService.consumeToSend(taskInfos);
    }
}
