package com.yeyeye.ezsender.pipline;

import com.yeyeye.ezsender.action.Processor;
import com.yeyeye.ezsender.enums.OperateCode;
import lombok.Data;


import java.util.List;
import java.util.Map;

/**
 * @author yeyeye
 * @Date 2023/4/7 21:19
 */
@Data
public class ProcessController {
    private Map<OperateCode, ProcessTemplate> processTemplateMap;

    public void process(ProcessContext context) {
        ProcessTemplate processTemplate = processTemplateMap.get(context.getRequest().getOperate());
        List<Processor> processorList = processTemplate.getProcessorList();
        for (Processor processor : processorList) {
            processor.process(context);
            if (context.isNeedBreak()) {
                break;
            }
        }
    }

    public void registry(OperateCode operateCode, ProcessTemplate template) {
        processTemplateMap.put(operateCode, template);
    }
}
