package com.yeyeye.ezsender.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yeyeye
 * @Date 2023/4/6 0:20
 */
public enum Params {
    MESSAGE_TEMPLATE_CODE("MESSAGE_TEMPLATE_CODE"),
    SIGN_NAME("SIGN_NAME"),

    MESSAGE_TEMPLATE_ID("MESSAGE_TEMPLATE_ID"),
    CREATOR("CREATOR"),
    RECEIVER("RECEIVER"),
    CONTENT("CONTENT");

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
