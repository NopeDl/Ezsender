package com.yeyeye.ezsender.handler.impl;

import com.yeyeye.ezsender.handler.Handler;
import com.yeyeye.ezsender.model.impl.MailParamModel;
import com.yeyeye.ezsender.pojo.TaskInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

/**
 * @author yeyeye
 * @Date 2023/4/19 23:47
 */
@Component
@Slf4j
public class MailHandler implements Handler {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void handle(TaskInfo taskInfo) {
        SimpleMailMessage message = new SimpleMailMessage();
        MailParamModel paramModel = (MailParamModel) taskInfo.getParamModel();
        message.setTo(taskInfo.getReceiver());
        message.setFrom(fromEmail);
        message.setSubject(paramModel.getTitle());
        message.setText(paramModel.getContent());
        javaMailSender.send(message);
    }
}
