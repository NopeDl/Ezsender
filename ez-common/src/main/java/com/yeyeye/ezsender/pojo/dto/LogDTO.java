package com.yeyeye.ezsender.pojo.dto;

import com.yeyeye.ezsender.enums.MessageStatus;
import lombok.Builder;
import lombok.Data;

/**
 * @author yeyeye
 * @Date 2023/4/25 23:20
 */
@Data
@Builder
public class LogDTO {
    private MessageStatus status;
    private Object msg;
}
