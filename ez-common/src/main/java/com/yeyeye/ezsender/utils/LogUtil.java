package com.yeyeye.ezsender.utils;

import com.alibaba.fastjson.JSON;
import com.yeyeye.ezsender.enums.MessageStatus;
import com.yeyeye.ezsender.pojo.dto.LogDTO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yeyeye
 * @Date 2023/4/26 21:28
 */
@Slf4j
public class LogUtil {
    public static void info(LogDTO logDTO) {
        log.info(JSON.toJSONString(logDTO));
    }

    public static void info(MessageStatus status, Object msg) {
        LogDTO logDTO = LogDTO.builder().status(status).msg(msg).build();
        info(logDTO);
    }
}
