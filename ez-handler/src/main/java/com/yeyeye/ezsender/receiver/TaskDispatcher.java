package com.yeyeye.ezsender.receiver;

import com.yeyeye.ezsender.enums.TaskType;
import com.yeyeye.ezsender.handler.Handler;
import com.yeyeye.ezsender.pojo.TaskInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Map<Integer, ThreadPoolExecutor> TASK_POOL_MAP = new HashMap<>();

    private static final Map<Integer, Handler> TASK_HANDLER_MAP = new HashMap<>();

    public TaskDispatcher registry(int taskType, ThreadPoolExecutor pool, Handler handler) {
        TASK_POOL_MAP.put(taskType, pool);
        TASK_HANDLER_MAP.put(taskType, handler);
        return this;
    }

    public TaskDispatcher registry(TaskType taskType, ThreadPoolExecutor pool, Handler handler) {
        TASK_POOL_MAP.put(taskType.getCode(), pool);
        TASK_HANDLER_MAP.put(taskType.getCode(), handler);
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
    public boolean dispatch(Task task) {
        try {
            TASK_POOL_MAP.get(task.getTaskInfo().getTaskType()).execute(task);
            return true;
        } catch (Exception e) {
            log.error("任务转发发生异常：{}，当前任务信息：{}", e.getMessage(), task.getTaskInfo());
            return false;
        }
    }
}
