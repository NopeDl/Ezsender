package com.yeyeye.ezsender.config;

import com.yeyeye.ezsender.enums.TaskType;
import com.yeyeye.ezsender.handler.impl.MailHandler;
import com.yeyeye.ezsender.handler.impl.SmsHandler;
import com.yeyeye.ezsender.receiver.RejectedHandler;
import com.yeyeye.ezsender.receiver.TaskDispatcher;
import com.yeyeye.ezsender.utils.ThreadPoolUtil;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yeyeye
 * @Date 2023/4/13 17:21
 */
@Configuration
public class MessageReceiverConfig {
    @Bean
    public TaskDispatcher taskDispatcher(SmsHandler smsHandler, MailHandler mailHandler) {
        return new TaskDispatcher()
                .registry(TaskType.SMS.getCode(), ThreadPoolUtil.newDefaultThreadPool(), smsHandler)
                .registry(TaskType.MAIL.getCode(), ThreadPoolUtil.newDefaultThreadPool(), mailHandler);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
