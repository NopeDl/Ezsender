package com.yeyeye.ezsender.factory;

import com.yeyeye.ezsender.enums.TaskType;
import com.yeyeye.ezsender.model.ParamModel;
import com.yeyeye.ezsender.model.impl.MailParamModel;
import com.yeyeye.ezsender.model.impl.SmsParamModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yeyeye
 * @Date 2023/4/20 17:10
 */
public class ParamModelFactory {
    private static final Map<Integer, Class<? extends ParamModel>> CLASS_MAP = new HashMap<>();

    static {
        registry(TaskType.SMS, SmsParamModel.class);
        registry(TaskType.MAIL, MailParamModel.class);
    }


    private static void registry(TaskType taskType, Class<? extends ParamModel> clazz) {
        CLASS_MAP.put(taskType.getCode(), clazz);
    }

    private ParamModelFactory() {
    }

    public static ParamModel getInstance(int taskType) {
        try {
            Constructor<? extends ParamModel> declaredConstructor = CLASS_MAP.get(taskType).getDeclaredConstructor();
            return declaredConstructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
