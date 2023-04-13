package com.yeyeye.ezsender.action.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yeyeye.ezsender.action.Processor;
import com.yeyeye.ezsender.enums.Params;
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
        Map<String, String> params = new HashMap<>();
        //根据模板ID查出模板具体参数
        MessageTemplate messageTemplate = messageTemplateMapper.selectById(request.getMessageTemplateId());
        if (messageTemplate == null) {
            context.setResponse(new SendResponse(ResponseStatus.ILLEGAL_PARAMS.getCode(), "模板ID错误"));
            context.setNeedBreak(true);
            return;
        }
        //将模板参数封装进任务信息
        //解析模板内容
        if (!JSONObject.isValid(messageTemplate.getContent())) {
            context.setResponse(new SendResponse(ResponseStatus.ILLEGAL_PARAMS.getCode(), "模板内容格式错误"));
            context.setNeedBreak(true);
            return;
        }
        JSONObject parsedContent = JSON.parseObject(messageTemplate.getContent());
        //放入无关参数
        parsedContent.forEach((k, v) -> {
            if (!Params.CONTENT.getContent().equals(k)) {
                params.put(k, (String) v);
            }
        });
        //处理占位符参数
        String replacedParam = replacePlaceholder(request.getParams(), (String) parsedContent.get(Params.CONTENT.getContent()));
        if (replacedParam != null) {
            params.put(Params.CONTENT.getContent(), replacedParam);
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

    /**
     * 替换占位符
     *
     * @param params  实际参数
     * @param content 占位符
     * @return Json
     */
    private String replacePlaceholder(Map<String, String> params, String content) {
        Map<String, String> placeholders = (Map<String, String>) JSONObject.parse(content);
        if (placeholders == null || placeholders.size() != params.size()) {
            return null;
        }
        placeholders.forEach((k, v) -> {
            placeholders.put(k, params.get(k));
        });
        return JSON.toJSONString(placeholders);
    }
}
