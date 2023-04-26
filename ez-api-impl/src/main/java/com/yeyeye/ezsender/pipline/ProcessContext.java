package com.yeyeye.ezsender.pipline;

import com.yeyeye.ezsender.pojo.SendRequest;
import com.yeyeye.ezsender.pojo.SendResponse;
import com.yeyeye.ezsender.pojo.TaskInfo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 处理上下文
 *
 * @author yeyeye
 * @Describe 整个处理链需要的数据或者结果都会储存在此
 * @Date 2023/4/7 17:01
 */
@Data
@Builder
public class ProcessContext {
    /**
     * 请求
     */
    private SendRequest request;
    /**
     * 响应
     */
    private SendResponse<?> response;
    /**
     * 上下文处理结果
     */
    private List<TaskInfo> taskInfos;
    /**
     * 上下文是否需要被打断
     */
    private boolean needBreak;
}
