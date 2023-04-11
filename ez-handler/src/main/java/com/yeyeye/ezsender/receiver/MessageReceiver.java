package com.yeyeye.ezsender.receiver;

import com.alibaba.fastjson.JSON;
import com.yeyeye.ezsender.enums.MQConstant;
import com.yeyeye.ezsender.pojo.TaskInfo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yeyeye
 * @Date 2023/4/11 22:36
 */
@Component
public class MessageReceiver {
    @RabbitListener(queues = MQConstant.QUEUE_NAME)
    public void receiver(List<TaskInfo> taskInfos) {
        System.out.println(taskInfos);
    }
}
