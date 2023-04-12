package com.yeyeye.ezsender.config;

import com.yeyeye.ezsender.action.impl.AssembleParamProcessor;
import com.yeyeye.ezsender.action.impl.PostCheckProcessor;
import com.yeyeye.ezsender.action.impl.PreCheckProcessor;
import com.yeyeye.ezsender.action.impl.SendMq;
import com.yeyeye.ezsender.enums.OperateCode;
import com.yeyeye.ezsender.pipline.ProcessController;
import com.yeyeye.ezsender.pipline.ProcessTemplate;
import com.yeyeye.ezsender.action.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author yeyeye
 * @Date 2023/4/7 22:08
 */
@Configuration
public class PipLineConfig {
    @Autowired
    private PreCheckProcessor preCheckProcessor;
    @Autowired
    private AssembleParamProcessor assembleParamProcessor;
    @Autowired
    private PostCheckProcessor postCheckProcessor;
    @Autowired
    private SendMq sendMq;

    /**
     * 普通发送处理模板
     *
     * @return 模板
     */
    @Bean
    public ProcessTemplate commonSendTemplate(ProcessController controller) {
        List<Processor> list = new ArrayList<>(Arrays.asList(preCheckProcessor, assembleParamProcessor, postCheckProcessor, sendMq));
        ProcessTemplate template = ProcessTemplate.builder().processorList(list).build();
        controller.registry(OperateCode.COMMON_SEND, template);
        return template;
    }

    /**
     * 流程控制器
     *
     * @return 流程控制器
     */
    @Bean
    public ProcessController processController() {
        ProcessController controller = new ProcessController();
        controller.setProcessTemplateMap(new HashMap<>());
        return controller;
    }
}
