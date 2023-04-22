package com.yeyeye.ezsender.receiver;

import com.yeyeye.ezsender.pojo.TaskInfo;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池拒绝策略
 *
 * @author yeyeye
 * @Date 2023/4/22 23:02
 */
public class RejectedHandler implements RejectedExecutionHandler {
    private final ConcurrentLinkedQueue<TaskInfo> queue;

    public RejectedHandler(ConcurrentLinkedQueue<TaskInfo> queue) {
        this.queue = queue;
    }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        //如果处理不了就先暂时加到队列中,一会交给receiver重新发回消息队列
        Task task = (Task) r;
        queue.add(task.getTaskInfo());
    }
}
