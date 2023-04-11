package com.yeyeye.ezsender.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @author yeyeye
 * @Date 2023/4/5 23:22
 * @Describe 发送短信需要的参数封装
 */
@Data
@Builder
public class SmsInfo {
    /**
     * 接收短信的手机号码,多个号码间用','隔开
     */
    private String phoneNumbers;
    /**
     * 签名
     */
    private String signName;
    /**
     * 模板
     */
    private String templateCode;
    /**
     * 模板参数（非必须），需要Json
     */
    private String templateParam;
    /**
     * 上行短信扩展码（非必须）
     */
    private String smsUpExtendCode;
    /**
     * 外部流水扩展字段（非必须）
     */
    private String outId;
}
