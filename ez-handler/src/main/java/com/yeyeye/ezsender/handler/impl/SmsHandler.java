package com.yeyeye.ezsender.handler.impl;

import com.yeyeye.ezsender.enums.Params;
import com.google.gson.Gson;
import com.yeyeye.ezsender.handler.Handler;
import com.yeyeye.ezsender.pojo.SmsInfo;
import com.yeyeye.ezsender.pojo.SmsRecord;
import com.yeyeye.ezsender.pojo.TaskInfo;
import com.yeyeye.ezsender.script.SmsScript;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author yeyeye
 * @Date 2023/4/4 19:26
 */
@Slf4j
@Component
public class SmsHandler implements Handler {
    @Autowired
    private SmsScript smsScript;

    @Override
    public void handle(TaskInfo taskInfo) {
        SmsRecord send = smsScript.send(SmsInfo.builder()
                .phoneNumbers(taskInfo.getReceiver())
                .templateCode(taskInfo.getMessageParams().get(Params.MESSAGE_TEMPLATE_CODE.getContent()))
                .signName(taskInfo.getMessageParams().get(Params.SIGN_NAME.getContent()))
                .templateParam(new Gson().toJson(taskInfo.getMessageParams()))
                .build());
        System.out.println(new Gson().toJson(send));
    }
}
