package com.yeyeye.ezsender.duplicate.rule;

import com.yeyeye.ezsender.duplicate.config.BaseDuplicateRuleConfig;
import com.yeyeye.ezsender.duplicate.config.MultiPushDuplicateConfig;
import com.yeyeye.ezsender.duplicate.config.SimilarDuplicateConfig;
import com.yeyeye.ezsender.duplicate.rule.impl.MultiPushDuplicateRule;
import com.yeyeye.ezsender.duplicate.rule.impl.SimilarDuplicateRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author yeyeye
 * @Date 2023/4/27 20:27
 */
@Component
public class DuplicateRuleFactory {
    @Autowired
    private ApplicationContext applicationContext;

    private DuplicateRuleFactory() {
    }

    public DuplicateRule getInstance(BaseDuplicateRuleConfig config) {
        if (DuplicateRuleEnum.SIMILAR.getCode().equals(config.getRule())) {
            //获取过滤器
            SimilarDuplicateRule bean = applicationContext.getBean(SimilarDuplicateRule.class);
            //设置过滤器的配置
            bean.setConfig((SimilarDuplicateConfig) config);
            return bean;
        } else if (DuplicateRuleEnum.MULTI_PUSH.getCode().equals(config.getRule())) {
            MultiPushDuplicateRule bean = applicationContext.getBean(MultiPushDuplicateRule.class);
            bean.setConfig((MultiPushDuplicateConfig) config);
            return bean;
        } else {
            return null;
        }
    }
}
