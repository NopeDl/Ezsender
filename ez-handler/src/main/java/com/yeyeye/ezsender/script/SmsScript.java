package com.yeyeye.ezsender.script;

import com.yeyeye.ezsender.pojo.SmsInfo;
import com.yeyeye.ezsender.pojo.SmsRecord;

/**
 * @author yeyeye
 * @Date 2023/4/5 23:22
 */
public interface SmsScript {
    /**
     * 发送短信
     *
     * @param info 发送短信需要的信息
     * @return 发送结果
     */
    SmsRecord send(SmsInfo info);
}
