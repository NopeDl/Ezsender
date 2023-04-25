package com.yeyeye.ezsender.enums;

import com.yeyeye.ezsender.model.ParamModel;
import com.yeyeye.ezsender.model.impl.MailParamModel;
import com.yeyeye.ezsender.model.impl.SmsParamModel;
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
    SMS(101, SmsParamModel.class),
    MAIL(102, MailParamModel.class);

    /**
     * 类型ID
     */
    private int code;

    private Class<? extends ParamModel> clazz;

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

    TaskType(int code, Class<? extends ParamModel> clazz) {
        this.code = code;
        this.clazz = clazz;
    }
}
