package com.yeyeye.ezsender.receiver;

import com.yeyeye.ezsender.enums.TaskType;
import com.yeyeye.ezsender.handler.Handler;
import com.yeyeye.ezsender.pojo.TaskInfo;

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
public class TaskDispatcher {
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
     * @param taskType 任务类型
     * @param tasks    任务列表
     */
    public void dispatch(int taskType, List<Task> tasks) {
        for (Task task : tasks) {
            TASK_POOL_MAP.get(taskType).execute(task);
        }
    }
}
