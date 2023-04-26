package com.yeyeye.ezsender.action.impl;

import com.yeyeye.ezsender.action.Processor;
import com.yeyeye.ezsender.enums.Params;
import com.yeyeye.ezsender.enums.ResponseStatus;
import com.yeyeye.ezsender.pipline.ProcessContext;
import com.yeyeye.ezsender.pojo.SendResponse;
import com.yeyeye.ezsender.pojo.TaskInfo;
import org.springframework.stereotype.Component;

import java.util.*;

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

        taskInfos.forEach((taskInfo) -> {
            //验证接受者格式是否匹配
            String receiverStr = taskInfo.getReceiver();
            //对接受者去重
            Set<String> receivers = new HashSet<>(List.of(receiverStr.split(",")));
            StringJoiner sj = new StringJoiner(",");
            for (String receiver : receivers) {
                if (!receiver.matches(PHONE_REGEX) && !receiver.matches(MAIL_REGEX)) {
                    context.setNeedBreak(true);
                    context.setResponse(new SendResponse<>(ResponseStatus.ILLEGAL_PARAMS.getCode(), "非法接受者: " + receiver));
                    return;
                }
                sj.add(receiver);
            }
            taskInfo.setReceiver(sj.toString());
        });
    }
}
