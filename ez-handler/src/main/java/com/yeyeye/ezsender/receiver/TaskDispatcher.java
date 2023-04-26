package com.yeyeye.ezsender.receiver;

import com.alibaba.fastjson.JSON;
import com.yeyeye.ezsender.enums.MessageStatus;
import com.yeyeye.ezsender.enums.TaskType;
import com.yeyeye.ezsender.handler.Handler;
import com.yeyeye.ezsender.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 分发任务
 *
 * @author yeyeye
 * @Date 2023/4/11 22:37
 */
@Slf4j
public class TaskDispatcher {
    private static final Map<Integer, ThreadPoolExecutor> TASK_POOL_MAP = new HashMap<>();

    private static final Map<Integer, Handler> TASK_HANDLER_MAP = new HashMap<>();

    public TaskDispatcher registry(int taskType, ThreadPoolExecutor pool, Handler handler) {
        TASK_POOL_MAP.put(taskType, pool);
        TASK_HANDLER_MAP.put(taskType, handler);
        return this;
    }

    public TaskDispatcher registry(TaskType taskType, ThreadPoolExecutor pool, Handler handler) {
        registry(taskType.getCode(), pool, handler);
        return this;
    }

    public Map<Integer, Handler> getTaskHandlerMap() {
        return TASK_HANDLER_MAP;
    }

    /**
     * 转发任务
     *
     * @param task 任务
     */
    public void dispatch(Task task) {
        try {
            TASK_POOL_MAP.get(task.getTaskInfo().getTaskType()).execute(task);
            LogUtil.info(MessageStatus.DISPATCHER_SUCCESS, task.getTaskInfo());
        } catch (Exception e) {
            LogUtil.info(MessageStatus.DISPATCHER_FAILED, task.getTaskInfo());
        }
    }
}
