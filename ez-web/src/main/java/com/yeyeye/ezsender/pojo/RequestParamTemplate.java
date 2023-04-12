package com.yeyeye.ezsender.pojo;

import lombok.Data;

/**
 * @author yeyeye
 * @Date 2023/4/7 20:27
 */
@Data
public class RequestParamTemplate {
    private String operate;
    /**
     * 操作类型
     */
    private Long messageTemplateId;
    private String creator;
    private String receiver;
    /**
     * 消息参数
     */
    private String content;

}
