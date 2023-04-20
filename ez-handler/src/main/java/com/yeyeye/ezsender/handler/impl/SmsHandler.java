package com.yeyeye.ezsender.handler.impl;

import com.alibaba.fastjson.JSON;
import com.yeyeye.ezsender.handler.Handler;
import com.yeyeye.ezsender.model.impl.SmsParamModel;
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
        SmsParamModel paramModel = (SmsParamModel) taskInfo.getParamModel();
        SmsRecord send = smsScript.send(SmsInfo.builder()
                .phoneNumbers(taskInfo.getReceiver())
                .templateCode(paramModel.getMessageTemplateCode())
                .signName(paramModel.getSignName())
                .templateParam(JSON.toJSONString(paramModel.getParams()))
                .build());
        System.out.println(JSON.toJSONString(send));
    }
}
