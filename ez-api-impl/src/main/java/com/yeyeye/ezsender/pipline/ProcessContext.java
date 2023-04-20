package com.yeyeye.ezsender.pipline;

import com.yeyeye.ezsender.model.ParamModel;
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
    private SendRequest request;
    private SendResponse<?> response;
    private List<TaskInfo> taskInfos;
    private boolean needBreak;
}
