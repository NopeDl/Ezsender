package com.yeyeye.ezsender.pipline;

import com.yeyeye.ezsender.action.Processor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yeyeye
 * @Date 2023/4/7 21:20
 */
@Data
@Builder
public class ProcessTemplate {
    /**
     * 责任链
     */
    private List<Processor> processorList;
}
