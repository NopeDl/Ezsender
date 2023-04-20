package com.yeyeye.ezsender.model.impl;

import com.yeyeye.ezsender.model.ParamModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yeyeye
 * @Date 2023/4/20 16:43
 */
@Data
@NoArgsConstructor
public class MailParamModel implements ParamModel {
    private String title;
    private String content;
}
