package com.yeyeye.ezsender.enums;

/**
 * @author yeyeye
 * @Date 2023/4/7 16:31
 */
public enum ResponseStatus {
    SEND_SUCCESS("OK", "发送成功"),
    ILLEGAL_REQUEST("ILLEGAL_REQUEST", "参数有误"),
    LOSING_PARAMS("LOSING_PARAMS", "缺少参数"),
    ILLEGAL_PARAMS("ILLEGAL_PARAMS", "非法参数"),
    SEND_FAIL("FAIL", "发送失败");

    private String code;
    private String content;

    ResponseStatus(String code, String content) {
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
