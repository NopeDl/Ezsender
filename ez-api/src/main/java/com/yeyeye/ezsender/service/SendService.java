package com.yeyeye.ezsender.service;

import com.yeyeye.ezsender.pojo.SendRequest;
import com.yeyeye.ezsender.pojo.SendResponse;

/**
 * @author yeyeye
 * @Date 2023/4/7 16:25
 */
public interface SendService {
    /**
     * 发送消息
     *
     * @param request 请求封装
     * @return 响应
     */
    SendResponse send(SendRequest request);
}
