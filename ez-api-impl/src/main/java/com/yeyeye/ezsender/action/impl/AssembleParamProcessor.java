package com.yeyeye.ezsender.action.impl;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yeyeye.ezsender.action.Processor;
import com.yeyeye.ezsender.enums.Params;
import com.yeyeye.ezsender.enums.ResponseStatus;
import com.yeyeye.ezsender.factory.ParamModelFactory;
import com.yeyeye.ezsender.mapper.MessageTemplateMapper;
import com.yeyeye.ezsender.model.ParamModel;
import com.yeyeye.ezsender.pipline.ProcessContext;
import com.yeyeye.ezsender.pojo.SendRequest;
import com.yeyeye.ezsender.pojo.SendResponse;
import com.yeyeye.ezsender.pojo.TaskInfo;
import com.yeyeye.ezsender.pojo.po.MessageTemplate;
import com.yeyeye.ezsender.utils.PlaceHolderUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 将request参数组装为TaskInfo
 *
 * @author yeyeye
 * @Date 2023/4/10 21:09
 */
@Component
public class AssembleParamProcessor implements Processor {
    @Resource
    private MessageTemplateMapper messageTemplateMapper;

    @Override
    public void process(ProcessContext context) {
        SendRequest request = context.getRequest();
        //根据模板ID查出模板具体参数
        MessageTemplate messageTemplate = messageTemplateMapper.selectById(request.getMessageTemplateId());
        if (messageTemplate == null) {
            context.setResponse(new SendResponse<>(ResponseStatus.ILLEGAL_PARAMS.getCode(), "模板ID错误"));
            context.setNeedBreak(true);
            return;
        }
        /**
         * 获取数据模型并且组合
         */
        ParamModel paramModel = getParamModel(request.getParams(), messageTemplate);

        //将模板参数封装进任务信息
        TaskInfo taskInfo = TaskInfo.builder()
                .messageTemplateId(request.getMessageTemplateId())
                .receiver(request.getReceiver())
                .paramModel(paramModel)
                .taskType(messageTemplate.getTaskType()).build();
        //将组装好的放入ProcessContext中
        ArrayList<TaskInfo> taskInfos = new ArrayList<>();
        taskInfos.add(taskInfo);
        context.setTaskInfos(taskInfos);
    }

    /**
     * @param params          实际参数，用来替换占位符
     * @param messageTemplate 消息模板
     * @return 参数模型
     */
    private ParamModel getParamModel(Map<String, String> params, MessageTemplate messageTemplate) {
        ParamModel paramModel = ParamModelFactory.getInstance(messageTemplate.getTaskType());
        if (paramModel == null) {
            throw new RuntimeException("获取参数模型失败");
        }
        //替换占位符
        JSONObject jsonObject = JSON.parseObject(messageTemplate.getContent());
        String jsonContent = (String) jsonObject.get(Params.CONTENT.getContent());
        //获取全部占位符
        List<String> replacements = PlaceHolderUtil.getParams(jsonContent);
        if (replacements.size() > 0) {
            //说明存在占位符，则使用用户传过来的参数替换
            String replaced = PlaceHolderUtil.replace(jsonContent, params);
            jsonObject.put(Params.CONTENT.getContent(), replaced);
            //给jsonObject中存入params集合
            Map<String, String> map = new HashMap<>();
            for (String replacement : replacements) {
                //将占位符和真实数据保存
                map.put(replacement, params.get(replacement));
            }
            jsonObject.put(Params.PARAMS.getContent(), map);
        }

        //获取模板内容
        Field[] fields = ReflectUtil.getFields(paramModel.getClass());
        for (Field field : fields) {
            ReflectUtil.setFieldValue(paramModel, field, jsonObject.get(field.getName()));
        }
        return paramModel;
    }
}
