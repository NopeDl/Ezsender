package com.yeyeye.ezsender.pojo;

import com.yeyeye.ezsender.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yeyeye
 * @Date 2023/4/7 16:25
 */
@Data
@AllArgsConstructor
public class SendResponse {
    /**
     * 响应状态码
     */
    private String code;

    /**
     * 响应内容
     */
    private String content;

    private SendResponse() {
    }

    public SendResponse(ResponseStatus status) {
        this.code = status.getCode();
        this.content = status.getContent();
    }

    /**
     * 默认无参发送成功
     *
     * @return 响应体
     */
    public static SendResponse success() {
        return new SendResponse(ResponseStatus.SEND_SUCCESS);
    }

    /**
     * 自定义消息内容发送成功
     *
     * @return 响应体
     */
    public static SendResponse success(String content) {
        return new SendResponse(ResponseStatus.SEND_SUCCESS.getCode(), content);
    }

    /**
     * 默认发送失败
     *
     * @return 响应体
     */
    public static SendResponse fail() {
        return new SendResponse(ResponseStatus.SEND_FAIL);
    }

    /**
     * 自定义发送失败
     *
     * @param content 内容
     * @return 响应体
     */
    public static SendResponse fail(String content) {
        return new SendResponse(ResponseStatus.SEND_FAIL.getCode(), content);
    }

    public static SendResponse fail(ResponseStatus status) {
        return new SendResponse(status.getCode(), status.getContent());
    }
}
