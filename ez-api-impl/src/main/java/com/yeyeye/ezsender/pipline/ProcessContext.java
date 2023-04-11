package com.yeyeye.ezsender.pipline;

import com.yeyeye.ezsender.pojo.SendRequest;
import com.yeyeye.ezsender.pojo.SendResponse;
import com.yeyeye.ezsender.pojo.TaskInfo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yeyeye
 * @Date 2023/4/7 17:01
 */
@Data
@Builder
public class ProcessContext {
    private SendRequest request;
    private SendResponse response;
    private List<TaskInfo> taskInfos;
    private boolean needBreak;
}
