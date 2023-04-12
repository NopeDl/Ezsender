package com.yeyeye.ezsender.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.yeyeye.ezsender.enums.OperateCode;
import com.yeyeye.ezsender.enums.Params;
import com.yeyeye.ezsender.enums.ResponseStatus;
import com.yeyeye.ezsender.mapper.MessageTemplateMapper;
import com.yeyeye.ezsender.pojo.RequestParamTemplate;
import com.yeyeye.ezsender.pojo.SendRequest;
import com.yeyeye.ezsender.pojo.SendResponse;
import com.yeyeye.ezsender.pojo.TaskInfo;
import com.yeyeye.ezsender.handler.Handler;
import com.yeyeye.ezsender.service.SendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yeyeye
 * @Date 2023/4/4 20:13
 */
@Slf4j
@Controller
public class SendController {
    @Autowired
    private Handler handler;

    @Autowired
    private SendService sendService;

    @Autowired
    private MessageTemplateMapper mapper;

    @RequestMapping("/test")
    @ResponseBody
    public void testMapper(){
        System.out.println(mapper.selectCount(null));
    }

//    @RequestMapping("/send1")
//    @ResponseBody
//    public String sendByNoneParam(@RequestParam("phoneNumber") String phoneNumber, @RequestParam("signName") String sign, @RequestParam("messageTemplateCode") String messageTemplateCode) {
//        Map<Params, String> senderParams = new HashMap<>();
//        senderParams.put(Params.SIGN_NAME, sign);
//        senderParams.put(Params.MESSAGE_TEMPLATE_CODE, messageTemplateCode);
//        handler.handle(TaskInfo.builder().senderParams(senderParams).receiver(phoneNumber).build());
//        return "OK";
//    }

    @RequestMapping("/send")
    @ResponseBody
    public SendResponse testSend(@RequestBody RequestParamTemplate template) {
        Map<String, String> params = JSON.parseObject(template.getContent(), new TypeReference<>() {
        });
        try {
            SendRequest sendRequest = SendRequest.builder()
                    .messageTemplateId(template.getMessageTemplateId())
                    .operate(OperateCode.get(template.getOperate()))
                    .params(params)
                    .creator(template.getCreator())
                    .receiver(template.getReceiver())
                    .build();
            return sendService.send(sendRequest);
        } catch (Exception e) {
            log.debug(e.getMessage());
            return SendResponse.fail(ResponseStatus.ILLEGAL_REQUEST);
        }
    }
}
