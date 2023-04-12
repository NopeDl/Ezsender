package com.yeyeye.ezsender.action;

import com.yeyeye.ezsender.pipline.ProcessContext;

/**
 * @author yeyeye
 * @Date 2023/4/7 21:20
 */
public interface Processor {
    void process(ProcessContext context);
}
