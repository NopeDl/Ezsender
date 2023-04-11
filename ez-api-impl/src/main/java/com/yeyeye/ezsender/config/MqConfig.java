package com.yeyeye.ezsender.config;


import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yeyeye.ezsender.enums.MQConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列配置
 *
 * @author yeyeye
 * @Date 2023/4/10 22:38
 */
@Configuration
public class MqConfig {
    @Bean
    public Queue directQueue() {
        return QueueBuilder.nonDurable(MQConstant.QUEUE_NAME).build();
    }

    @Bean
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange(MQConstant.EXCHANGE_NAME).build();
    }

    @Bean
    public Binding directBinding(Queue queue, Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(MQConstant.ROUTING_KEY).noargs();
    }
}
