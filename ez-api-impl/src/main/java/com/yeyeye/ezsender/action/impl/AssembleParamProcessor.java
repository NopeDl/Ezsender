package com.yeyeye.ezsender.action.impl;

import com.yeyeye.ezsender.action.Processor;
import com.yeyeye.ezsender.enums.ResponseStatus;
import com.yeyeye.ezsender.mapper.MessageTemplateMapper;
import com.yeyeye.ezsender.pipline.ProcessContext;
import com.yeyeye.ezsender.pojo.SendRequest;
import com.yeyeye.ezsender.pojo.SendResponse;
import com.yeyeye.ezsender.pojo.TaskInfo;
import com.yeyeye.ezsender.pojo.po.MessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.yeyeye.ezsender.enums.Params.MESSAGE_TEMPLATE_ID;

/**
 * 将request参数组装为TaskInfo
 *
 * @author yeyeye
 * @Date 2023/4/10 21:09
 */
@Component
public class AssembleParamProcessor implements Processor {
    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    @Override
    public void process(ProcessContext context) {
        SendRequest request = context.getRequest();
        //复制一份
        Map<String, String> params;
        if (request.getParams() != null) {
            params = new HashMap<>(request.getParams());
        } else {
            params = new HashMap<>();
        }
        params.put(MESSAGE_TEMPLATE_ID.getContent(), String.valueOf(request.getMessageTemplateId()));
        //组装模板
        MessageTemplate messageTemplate = messageTemplateMapper.selectById(request.getMessageTemplateId());
        if (messageTemplate == null) {
            context.setResponse(SendResponse.fail(ResponseStatus.ILLEGAL_PARAMS));
            context.setNeedBreak(true);
            return;
        }
        TaskInfo taskInfo = TaskInfo.builder()
                .messageTemplateId(request.getMessageTemplateId())
                .receiver(request.getReceiver())
                .messageParams(params)
                .taskType(messageTemplate.getTaskType()).build();
        //将组装好的放入ProcessContext中
        ArrayList<TaskInfo> taskInfos = new ArrayList<>();
        taskInfos.add(taskInfo);
        context.setTaskInfos(taskInfos);
    }
}
