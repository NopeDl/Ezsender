package com.yeyeye.ezsender.action.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yeyeye.ezsender.action.Processor;
import com.yeyeye.ezsender.enums.MQConstant;
import com.yeyeye.ezsender.enums.MessageStatus;
import com.yeyeye.ezsender.pipline.ProcessContext;
import com.yeyeye.ezsender.utils.LogUtil;
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
        String json = JSON.toJSONString(context.getTaskInfos(), SerializerFeature.WriteClassName);
        rabbitTemplate.convertAndSend(
                MQConstant.EXCHANGE_NAME,
                MQConstant.ROUTING_KEY,
                json);
        LogUtil.info(MessageStatus.SEND_MQ, json);
    }
}
