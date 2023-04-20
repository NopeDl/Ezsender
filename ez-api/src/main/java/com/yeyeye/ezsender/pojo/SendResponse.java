package com.yeyeye.ezsender.pojo;

import com.yeyeye.ezsender.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yeyeye
 * @Date 2023/4/7 16:25
 */
@Data
public class SendResponse<T> {
    /**
     * 响应状态码
     */
    private String code;

    /**
     * 响应内容
     */
    private String content;

    private T data;

    private SendResponse() {
    }

    public SendResponse(ResponseStatus status) {
        this.code = status.getCode();
        this.content = status.getContent();
    }

    public SendResponse(String code, String content) {
        this.code = code;
        this.content = content;
    }

    public SendResponse(T data) {
        this.code = ResponseStatus.SEND_SUCCESS.getCode();
        this.content = ResponseStatus.SEND_SUCCESS.getContent();
        this.data = data;
    }

    /**
     * 默认无参发送成功
     *
     * @return 响应体
     */
    public static SendResponse<Void> success() {
        return new SendResponse<>(ResponseStatus.SEND_SUCCESS);
    }

    /**
     * 自定义消息内容发送成功
     *
     * @return 响应体
     */
    public static SendResponse<Void> success(String content) {
        return new SendResponse<>(ResponseStatus.SEND_SUCCESS.getCode(), content);
    }

    /**
     * 默认发送失败
     *
     * @return 响应体
     */
    public static SendResponse<Void> fail() {
        return new SendResponse<>(ResponseStatus.SEND_FAIL);
    }

    /**
     * 自定义发送失败
     *
     * @param content 内容
     * @return 响应体
     */
    public static SendResponse<Void> fail(String content) {
        return new SendResponse<>(ResponseStatus.SEND_FAIL.getCode(), content);
    }

    public static SendResponse<Void> fail(ResponseStatus status) {
        return new SendResponse<>(status.getCode(), status.getContent());
    }

    public static SendResponse<Void> fail(ResponseStatus status, String content) {
        return new SendResponse<>(status.getCode(), content);
    }
}
