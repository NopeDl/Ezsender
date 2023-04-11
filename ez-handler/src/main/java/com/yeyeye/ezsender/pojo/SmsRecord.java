package com.yeyeye.ezsender.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author yeyeye
 * @Date 2023/4/5 23:26
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SmsRecord {
    /**
     * 请求状态码
     * 返回OK代表请求成功。
     */
    private String code;
    /**
     * 状态码的描述
     */
    private String message;
    /**
     * 发送回执ID
     */
    private String bizId;
    /**
     * 请求ID
     */
    private String requestId;

    public SmsRecord(String message) {
        this.message = message;
    }
}
