package com.yeyeye.ezsender.handler.impl;

import com.alibaba.fastjson.JSON;
import com.yeyeye.ezsender.enums.MessageStatus;
import com.yeyeye.ezsender.enums.Params;
import com.yeyeye.ezsender.handler.Handler;
import com.yeyeye.ezsender.model.impl.SmsParamModel;
import com.yeyeye.ezsender.pojo.SmsInfo;
import com.yeyeye.ezsender.pojo.SmsRecord;
import com.yeyeye.ezsender.pojo.TaskInfo;
import com.yeyeye.ezsender.script.SmsScript;
import com.yeyeye.ezsender.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;


/**
 * @author yeyeye
 * @Date 2023/4/4 19:26
 */
@Component
public class SmsHandler implements Handler {
    @Autowired
    private SmsScript smsScript;

    @Override
    public void handle(TaskInfo taskInfo) {
        SmsParamModel paramModel = (SmsParamModel) taskInfo.getParamModel();
        Set<String> receivers = taskInfo.getReceiver();
        StringJoiner sj = new StringJoiner(",");
        for (String receiver : receivers) {
            sj.add(receiver);
        }
        String receiver = sj.toString();
        SmsRecord record = smsScript.send(SmsInfo.builder()
                .phoneNumbers(receiver)
                .templateCode(paramModel.getMessageTemplateCode())
                .signName(paramModel.getSignName())
                .templateParam(JSON.toJSONString(paramModel.getParams()))
                .build());
        Map<String, Object> res = new HashMap<>(2);
        res.put(Params.BUSINESS_ID.getContent(), taskInfo.getBusinessId());
        res.put(Params.RECORDS.getContent(), record);
        LogUtil.info(MessageStatus.SEND_API, res);
    }
}
