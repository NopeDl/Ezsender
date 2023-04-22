package com.yeyeye.ezsender.action.impl;

import com.yeyeye.ezsender.action.Processor;
import com.yeyeye.ezsender.enums.Params;
import com.yeyeye.ezsender.enums.ResponseStatus;
import com.yeyeye.ezsender.pipline.ProcessContext;
import com.yeyeye.ezsender.pojo.SendRequest;
import com.yeyeye.ezsender.pojo.SendResponse;
import org.springframework.stereotype.Component;


/**
 * @author yeyeye
 * @Date 2023/4/7 21:21
 */
@Component
public class PreCheckProcessor implements Processor {

    @Override
    public void process(ProcessContext context) {
        SendRequest request = context.getRequest();
        if (request == null) {
            context.setNeedBreak(true);
            context.setResponse(new SendResponse<>(ResponseStatus.ILLEGAL_REQUEST));
            return;
        }
        if (request.getMessageTemplateId() == null) {
            context.setNeedBreak(true);
            context.setResponse(new SendResponse<>(ResponseStatus.LOSING_PARAMS.getCode(), Params.MESSAGE_TEMPLATE_ID.getContent()));
            return;
        }
        if (request.getCreator() == null) {
            context.setNeedBreak(true);
            context.setResponse(new SendResponse<>(ResponseStatus.LOSING_PARAMS.getCode(), Params.CREATOR.getContent()));
            return;
        }
        if (request.getReceiver() == null) {
            context.setNeedBreak(true);
            context.setResponse(new SendResponse<>(ResponseStatus.LOSING_PARAMS.getCode(), Params.RECEIVER.getContent()));
            return;
        }
    }
}
