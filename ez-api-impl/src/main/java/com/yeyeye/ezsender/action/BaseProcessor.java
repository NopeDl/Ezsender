package com.yeyeye.ezsender.action;

import com.yeyeye.ezsender.pipline.ProcessContext;
import com.yeyeye.ezsender.pipline.Processor;

/**
 * @author yeyeye
 * @Date 2023/4/7 21:40
 */
public abstract class BaseProcessor implements Processor {
    @Override
    public void process(ProcessContext context) {
        if (context.isNeedBreak()) {
            return;
        }
        doProcess(context);
    }

    protected abstract void doProcess(ProcessContext context);
}
