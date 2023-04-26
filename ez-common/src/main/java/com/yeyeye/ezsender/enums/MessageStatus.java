package com.yeyeye.ezsender.enums;

import lombok.Data;

/**
 * @author yeyeye
 * @Date 2023/4/26 21:29
 */
public enum MessageStatus {
    SEND_MQ("001", "已发送至MQ"),
    CONSUME("002", "消息已被消费"),
    DISPATCHER_SUCCESS("003", "转发失败"),
    DISPATCHER_FAILED("004", "转发成功"),
    SEND_API("005", "发送至第三方API"),
    ERROR("000", "错误异常");
    private final String code;
    private final String content;

    MessageStatus(String code, String content) {
        this.code = code;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public String getContent() {
        return content;
    }
}
