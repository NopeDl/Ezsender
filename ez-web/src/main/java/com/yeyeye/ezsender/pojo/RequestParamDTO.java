package com.yeyeye.ezsender.pojo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author yeyeye
 * @Date 2023/4/7 20:27
 */
@Data
public class RequestParamDTO {
    @NotNull(message = "操作不能为空")
    @NotBlank(message = "操作不能为空")
    private String operate;
    /**
     * 操作类型
     */
    @NotNull(message = "模板ID不能为空")
    private Long messageTemplateId;
    @NotNull(message = "发送方不能为空")
    @NotBlank(message = "发送方不能为空")
    private String creator;
    @NotNull(message = "接收方不能为空")
    @NotBlank(message = "接收方不能为空")
    private String receiver;
    /**
     * 消息参数
     */
    private String content;

}
