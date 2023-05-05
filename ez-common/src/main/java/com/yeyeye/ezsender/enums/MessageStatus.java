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
    DUPLICATE_SUCCESS("005", "去重成功"),
    SIMILAR_DUPLICATE("005101", "101去重"),
    MULTI_PUSH_DUPLICATE("005102", "102去重"),
    DUPLICATE_FAILED("006", "去重失败"),
    SEND_API("007", "发送至第三方API"),
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
