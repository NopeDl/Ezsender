package com.yeyeye.ezsender.action.impl;

import com.yeyeye.ezsender.action.Processor;
import com.yeyeye.ezsender.pipline.ProcessContext;
import com.yeyeye.ezsender.pojo.SendRequest;
import com.yeyeye.ezsender.pojo.TaskInfo;
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
        //将组装好的放入ProcessContext中
        TaskInfo taskInfo = TaskInfo.builder().receiver(request.getReceiver()).messageParams(params).build();
        ArrayList<TaskInfo> taskInfos = new ArrayList<>();
        taskInfos.add(taskInfo);
        context.setTaskInfos(taskInfos);
    }
}
