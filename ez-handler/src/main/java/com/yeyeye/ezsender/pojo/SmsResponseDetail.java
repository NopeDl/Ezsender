package com.yeyeye.ezsender.pojo;

import lombok.Builder;
import lombok.Data;

/**
 * @author yeyeye
 * @Date 2023/4/5 23:27
 */
@Data
@Builder
public class SmsResponseDetail {
    /**
     * 运营商短信状态码
     * DELIVERED-发送成功
     * 其他错误码-发送失败
     */
    private String errorCode;
    /**
     * 短信模板ID
     */
    private String templateCode;
    /**
     * 外部流水扩展字段
     */
    private String outId;
    /**
     * 短信接收日期和时间
     */
    private String receiveDate;
    /**
     * 短信发送日期和时间
     */
    private String sendDate;
    /**
     * 接受短信的手机号码
     */
    private String phoneNum;
    /**
     * 短信内容
     */
    private String content;
    /**
     * 短信发送状态
     * 1-等待回执
     * 2-发送失败
     * 3-发送成功
     */
    private Long sendStatus;
}
