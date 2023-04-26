package com.yeyeye.ezsender.utils;

import com.yeyeye.ezsender.pojo.po.MessageTemplate;

/**
 * @author yeyeye
 * @Date 2023/4/25 22:04
 */
public class BusinessUtil {
    /**
     * 生成业务ID,规则：“任务类型 + 时间戳”
     *
     * @param messageTemplate 消息模板
     * @return 业务ID
     */
    public static Long generateBusinessId(MessageTemplate messageTemplate) {
        Integer taskType = messageTemplate.getTaskType();
        String res = String.valueOf(taskType) +
                System.currentTimeMillis();
        return Long.valueOf(res);
    }
}
