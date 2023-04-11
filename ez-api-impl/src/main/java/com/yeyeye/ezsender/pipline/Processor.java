package com.yeyeye.ezsender.pipline;

/**
 * @author yeyeye
 * @Date 2023/4/7 21:20
 */
public interface Processor {
    void process(ProcessContext context);
}
