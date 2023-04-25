package com.yeyeye.ezsender.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yeyeye.ezsender.enums.OperateCode;
import com.yeyeye.ezsender.mapper.MessageTemplateMapper;
import com.yeyeye.ezsender.pojo.RequestParamDTO;
import com.yeyeye.ezsender.pojo.SendRequest;
import com.yeyeye.ezsender.pojo.SendResponse;
import com.yeyeye.ezsender.service.ConsumeService;
import com.yeyeye.ezsender.service.SendService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yeyeye
 * @Date 2023/4/4 20:13
 */
@Slf4j
@Controller
@RequestMapping
public class SendController {
    @Autowired
    private SendService sendService;

    @Autowired
    private MessageTemplateMapper mapper;

    @RequestMapping("/test")
    @ResponseBody
    public String testMapper() {
        return String.valueOf(mapper.selectCount(null));
    }

    @PostMapping("/send")
    @ResponseBody
    public SendResponse<?> sendSms(@RequestBody @Valid RequestParamDTO template) {
        Map<String, String> params = JSON.parseObject(template.getContent(), new TypeReference<>() {
        });
        SendRequest sendRequest = SendRequest.builder()
                .messageTemplateId(template.getMessageTemplateId())
                .operate(OperateCode.get(template.getOperate()))
                .params(params)
                .creator(template.getCreator())
                .receiver(template.getReceiver())
                .build();
        return sendService.send(sendRequest);
    }
}
