package com.yeyeye.ezsender.pojo.po;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 数据消息模板
 *
 * @author yeyeye
 * @Date 2023/4/12 20:30
 */
@Data
@TableName("message_template")
public class MessageTemplate {
    @TableId
    private Long messageTemplateId;
    private Integer taskType;
    private String content;
}
