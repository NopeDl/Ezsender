package com.yeyeye.ezsender.service;

import com.yeyeye.ezsender.pipline.ProcessContext;
import com.yeyeye.ezsender.pipline.ProcessController;
import com.yeyeye.ezsender.pojo.SendRequest;
import com.yeyeye.ezsender.pojo.SendResponse;
import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Service;

/**
 * @author yeyeye
 * @Date 2023/4/7 16:40
 */
@Service
public class SendServiceImpl implements SendService {
    @Autowired
    private ProcessController processController;

    @Override
    public SendResponse<?> send(SendRequest request) {
        ProcessContext context = ProcessContext.builder().request(request).response(SendResponse.success()).build();
        processController.process(context);
        return context.getResponse();
    }
}
