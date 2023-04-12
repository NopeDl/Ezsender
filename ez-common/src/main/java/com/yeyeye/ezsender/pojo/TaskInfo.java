package com.yeyeye.ezsender.pojo;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author yeyeye
 * @Date 2023/4/4 19:54
 */
@Data
@Builder
public class TaskInfo {
    private Long messageTemplateId;
    /**
     * 消息接受者
     */
    private String receiver;

    /**
     * 填充模板的参数
     */
    private Map<String, String> messageParams;

}
