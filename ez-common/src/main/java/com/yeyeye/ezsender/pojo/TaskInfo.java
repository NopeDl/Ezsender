package com.yeyeye.ezsender.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author yeyeye
 * @Date 2023/4/4 19:54
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskInfo {
    /**
     * 任务所属模板ID
     */
    private Long messageTemplateId;

    /**
     * 任务所属类型 短信、邮箱或者其他
     */
    private Integer taskType;
    /**
     * 消息接受者
     */
    private String receiver;

    /**
     * 填充模板的参数
     */
    private Map<String, String> messageParams;
}
