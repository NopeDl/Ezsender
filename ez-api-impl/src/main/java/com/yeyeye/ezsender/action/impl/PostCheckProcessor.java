package com.yeyeye.ezsender.action.impl;

import com.yeyeye.ezsender.action.Processor;
import com.yeyeye.ezsender.enums.Params;
import com.yeyeye.ezsender.enums.ResponseStatus;
import com.yeyeye.ezsender.pipline.ProcessContext;
import com.yeyeye.ezsender.pojo.SendResponse;
import com.yeyeye.ezsender.pojo.TaskInfo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 处理掉非法参数
 *
 * @author yeyeye
 * @Date 2023/4/10 21:35
 */
@Component
public class PostCheckProcessor implements Processor {
    private static final String PHONE_REGEX = "^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\\d{8}$";
    private static final String MAIL_REGEX = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";

    @Override
    public void process(ProcessContext context) {
        //检查参数
        List<TaskInfo> taskInfos = context.getTaskInfos();
        if (taskInfos == null || taskInfos.size() == 0) {
            context.setNeedBreak(true);
            context.setResponse(SendResponse.fail(ResponseStatus.LOSING_PARAMS));
            return;
        }
//        //说明出现未规定的参数
//        if (taskInfos.size() > Params.SIZE) {
//            context.setNeedBreak(true);
//            context.setResponse(SendResponse.fail(ResponseStatus.ILLEGAL_PARAMS));
//            return;
//        }

        taskInfos.forEach((taskInfo) -> {
            //验证接受者格式是否匹配
            String receiver = taskInfo.getReceiver();
            if (!receiver.matches(PHONE_REGEX) && !receiver.matches(MAIL_REGEX)) {
                context.setNeedBreak(true);
                context.setResponse(new SendResponse(ResponseStatus.ILLEGAL_PARAMS.getCode(), "非法接受者: " + receiver));
                return;
            }

//            //验证参数
//            Map<String, String> messageParams = taskInfo.getMessageParams();
//            for (String key : messageParams.keySet()) {
//                //不是规定参数
//                if (Params.get(key) == null) {
//                    context.setNeedBreak(true);
//                    context.setResponse(new SendResponse(ResponseStatus.ILLEGAL_PARAMS.getCode(), "非法参数: " + key));
//                    return;
//                }
//
//                //参数为null或为空串
//                if (messageParams.get(key) == null || "".equals(messageParams.get(key))) {
//                    context.setNeedBreak(true);
//                    context.setResponse(new SendResponse(ResponseStatus.ILLEGAL_PARAMS.getCode(), "参数不能为空: " + key));
//                    return;
//                }
//            }
        });
    }
}
