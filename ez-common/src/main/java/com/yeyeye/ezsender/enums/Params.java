package com.yeyeye.ezsender.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 参数
 *
 * @author yeyeye
 * @Date 2023/4/6 0:20
 */
public enum Params {
    /**
     * 模板Code（短信）
     */
    MESSAGE_TEMPLATE_CODE("messageTemplateCode"),
    /**
     * 业务ID，每个请求到达API层都会创建一个
     */
    BUSINESS_ID("businessId"),
    /**
     * 短信第三方发送结果
     */
    RECORDS("records"),

    /**
     * 模板签名（短信）
     */
    SIGN_NAME("signName"),

    /**
     * 模板ID
     */
    MESSAGE_TEMPLATE_ID("messageTemplateId"),

    CREATOR("creator"),

    RECEIVER("receiver"),

    CONTENT("content"),

    TITLE("title"),

    PARAMS("params"),
    /**
     * 规则ID
     */
    RULE("rule"),
    /**
     * 时间限制，单位为秒(s)
     */
    LIMIT("limit");

    private final String content;
    public static final int SIZE;

    private static final Map<String, Params> MAP;

    static {
        MAP = new HashMap<>();
        for (Params value : values()) {
            MAP.put(value.getContent(), value);
        }
        SIZE = MAP.size();
    }

    Params(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public static Params get(String v) {
        return MAP.get(v);
    }
}
