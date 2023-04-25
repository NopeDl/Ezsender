package com.yeyeye.ezsender.controller;

import com.alibaba.fastjson.JSONObject;
import com.yeyeye.ezsender.enums.ResponseStatus;
import com.yeyeye.ezsender.mapper.MessageTemplateMapper;
import com.yeyeye.ezsender.pojo.SendResponse;
import com.yeyeye.ezsender.pojo.po.MessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yeyeye
 * @Date 2023/4/12 21:42
 */
@Controller
@RequestMapping("/template")
public class messageTemplateController {
    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    @RequestMapping("/add")
    @ResponseBody
    public SendResponse<?> addTemplate(@RequestBody MessageTemplate messageTemplate) {
        int insert = messageTemplateMapper.insert(messageTemplate);
        if (insert > 0) {
            return new SendResponse<>("新建成功", insert + "");
        } else {
            return SendResponse.fail(ResponseStatus.ILLEGAL_PARAMS);
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    public List<MessageTemplate> getAll() {
        return messageTemplateMapper.selectList(null);
    }

}
