package com.yeyeye.ezsender.action.impl;

import com.alibaba.fastjson.JSON;
import com.yeyeye.ezsender.action.Processor;
import com.yeyeye.ezsender.enums.MQConstant;
import com.yeyeye.ezsender.pipline.ProcessContext;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yeyeye
 * @Date 2023/4/10 22:28
 */
@Component
public class SendMq implements Processor {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void process(ProcessContext context) {
        String taskInfoStr = JSON.toJSONString(context.getTaskInfos());
        rabbitTemplate.convertAndSend(MQConstant.EXCHANGE_NAME, MQConstant.ROUTING_KEY, taskInfoStr);
    }
}
