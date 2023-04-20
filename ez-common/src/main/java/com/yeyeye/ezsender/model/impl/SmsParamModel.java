package com.yeyeye.ezsender.model.impl;

import com.yeyeye.ezsender.model.ParamModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 短信数据模板
 *
 * @author yeyeye
 * @Date 2023/4/20 16:37
 */
@Data
@NoArgsConstructor
public class SmsParamModel implements ParamModel {
    private String messageTemplateCode;
    private String signName;
    private Map<String, String> params;
}
