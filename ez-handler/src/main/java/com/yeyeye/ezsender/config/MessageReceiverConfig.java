package com.yeyeye.ezsender.config;

import com.yeyeye.ezsender.enums.TaskType;
import com.yeyeye.ezsender.handler.Handler;
import com.yeyeye.ezsender.handler.impl.SmsHandler;
import com.yeyeye.ezsender.receiver.TaskDispatcher;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yeyeye
 * @Date 2023/4/13 17:21
 */
@Configuration
public class MessageReceiverConfig {
    @Bean
    public TaskDispatcher taskDispatcher() {
        ThreadPoolExecutor smsPool = new ThreadPoolExecutor(4,
                6,
                1,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5));
        return new TaskDispatcher().registry(TaskType.SMS.getCode(), smsPool);
    }

    @Bean
    public Map<Integer, Handler> handlerMap(SmsHandler smsHandler) {
        Map<Integer, Handler> handlerMap = new HashMap<>();
        handlerMap.put(TaskType.SMS.getCode(), smsHandler);
        return handlerMap;
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
