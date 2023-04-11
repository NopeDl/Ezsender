package com.yeyeye.ezsender.pojo;

import com.yeyeye.ezsender.enums.OperateCode;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * @author yeyeye
 * @Date 2023/4/7 16:25
 */
@Data
@Builder
public class SendRequest {
    /**
     * 操作类型
     */
    private OperateCode operate;
    private Long messageTemplateId;
    private String creator;
    private String receiver;
    /**
     * 消息参数
     */
    private Map<String, String> params;

}
