package com.yeyeye.ezsender.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yeyeye
 * @Date 2023/4/11 16:49
 */
@RestController
public class SendMessageController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/sendDirectMessage")
    public String sendDirectMessage() {
        String messageData = "test message, hello!";
        Map<String, Object> map = new HashMap<>();
        map.put("messageData", messageData);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("ezsExchange", "/ezs", map);
        return "ok";
    }

}
