package com.yeyeye.ezsender.controller;

import com.yeyeye.ezsender.mapper.MessageTemplateMapper;
import com.yeyeye.ezsender.pojo.SendResponse;
import com.yeyeye.ezsender.pojo.po.MessageTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public SendResponse addTemplate(@RequestBody MessageTemplate messageTemplate) {
        int insert = messageTemplateMapper.insert(messageTemplate);
        return new SendResponse("新建成功", insert + "");
    }

}
