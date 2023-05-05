package com.yeyeye.ezsender.handler.impl;

import com.yeyeye.ezsender.enums.MessageStatus;
import com.yeyeye.ezsender.handler.Handler;
import com.yeyeye.ezsender.model.impl.MailParamModel;
import com.yeyeye.ezsender.pojo.TaskInfo;
import com.yeyeye.ezsender.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.StringJoiner;

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
        Set<String> receivers = taskInfo.getReceiver();
        StringJoiner sj = new StringJoiner(",");
        for (String receiver : receivers) {
            sj.add(receiver);
        }
        String receiver = sj.toString();
        SimpleMailMessage message = new SimpleMailMessage();
        MailParamModel paramModel = (MailParamModel) taskInfo.getParamModel();
        message.setTo(receiver);
        message.setFrom(fromEmail);
        message.setSubject(paramModel.getTitle());
        message.setText(paramModel.getContent());
        javaMailSender.send(message);
        LogUtil.info(MessageStatus.SEND_API, taskInfo);
    }
}
