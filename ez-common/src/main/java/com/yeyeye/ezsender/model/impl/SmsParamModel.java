package com.yeyeye.ezsender.model.impl;

import com.yeyeye.ezsender.model.ParamModel;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * 短信数据模板
 *
 * @author yeyeye
 * @Date 2023/4/20 16:37
 */
@Data
@Builder
public class SmsParamModel implements ParamModel {
    private String messageTemplateCode;
    private String signName;
    private Map<String, String> params;
}
