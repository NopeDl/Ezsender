package com.yeyeye.ezsender.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务类型，短信或邮箱啥的
 *
 * @author yeyeye
 * @Date 2023/4/12 22:00
 */
@Getter
public enum TaskType {
    SMS(101);

    /**
     * 类型ID
     */
    private int code;

    private static final Map<Integer, TaskType> MAP;

    static {
        MAP = new HashMap<>();
        for (TaskType value : TaskType.values()) {
            MAP.put(value.code, value);
        }
    }

    public static TaskType get(int taskType) {
        return MAP.get(taskType);
    }

    TaskType(int code) {
        this.code = code;
    }
}
