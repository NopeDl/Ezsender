package com.yeyeye.ezsender.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yeyeye
 * @Date 2023/4/7 21:01
 */
public enum OperateCode {
    COMMON_SEND("send");

    private String operate;

    OperateCode(String operate) {
        this.operate = operate;
    }

    private static Map<String, OperateCode> MAP = new HashMap<>();

    static {
        for (OperateCode value : OperateCode.values()) {
            register(value.operate, value);
        }
    }

    public static OperateCode get(String operate) {
        return MAP.get(operate.toLowerCase());
    }

    private static void register(String operate, OperateCode operateCode) {
        MAP.put(operate, operateCode);
    }
}
