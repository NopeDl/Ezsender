package com.yeyeye.ezsender.utils;

import cn.hutool.cron.task.Task;
import com.yeyeye.ezsender.pojo.TaskInfo;

import java.util.concurrent.*;

/**
 * @author yeyeye
 * @Date 2023/4/22 22:32
 */
public class ThreadPoolUtil {
    public static final int CORE_POOL_SIZE = 4;
    public static final int MAX_MUM_POOL_SIZE = 10;
    public static final int DEFAULT_KEEP_ALIVE_TIME = 10;
    public static final int DEFAULT_BLOCKING_QUEUE_SIZE = 100;

    /**
     * 创建一个默认线程池
     *
     * @return 线程池对象
     */
    public static ThreadPoolExecutor newDefaultThreadPool() {
        return newDefaultThreadPool(new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 创建一个默认线程池
     *
     * @return 线程池对象
     */
    public static ThreadPoolExecutor newDefaultThreadPool(RejectedExecutionHandler r) {
        return new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_MUM_POOL_SIZE,
                DEFAULT_KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(DEFAULT_BLOCKING_QUEUE_SIZE),
                r);
    }
}
