package com.yeyeye.ezsender.duplicate.service.impl;

import cn.hutool.core.util.ReflectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yeyeye.ezsender.duplicate.config.BaseDuplicateRuleConfig;
import com.yeyeye.ezsender.duplicate.config.DuplicateRuleConfig;
import com.yeyeye.ezsender.duplicate.rule.DuplicateRule;
import com.yeyeye.ezsender.duplicate.rule.DuplicateRuleEnum;
import com.yeyeye.ezsender.duplicate.rule.DuplicateRuleFactory;
import com.yeyeye.ezsender.duplicate.service.DuplicateService;
import com.yeyeye.ezsender.enums.Params;
import com.yeyeye.ezsender.pojo.TaskInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author yeyeye
 * @Date 2023/4/26 22:30
 */
@Component
public class DuplicateServiceImpl implements DuplicateService {
    @Autowired
    private DuplicateRuleFactory duplicateRuleFactory;
    @Autowired
    private DuplicateRuleConfig duplicateRuleConfig;

    @Override
    public void duplicateTask(TaskInfo taskInfo) {
        //Todo 获取规则
//        //规则一： 同一用户 五分钟内 被推送多次同一模板，单位为s
        String rule1 = duplicateRuleConfig.getRule1();
//        //规则二： 同一用户 一天内 被推送多次消息
        String rule2 = duplicateRuleConfig.getRule2();
        List<String> configs = new ArrayList<>();
        configs.add(rule1);
        configs.add(rule2);
        List<BaseDuplicateRuleConfig> rules = new ArrayList<>();
        for (String config : configs) {
            rules.add(assembleConfig(config));
        }
        //封装并添加规则
        //根据规则去重
        for (BaseDuplicateRuleConfig config : rules) {
            //都用单例，可能会在规则变更时有线程安全问题，这里暂时不解决
            DuplicateRule duplicateRule = duplicateRuleFactory.getInstance(config);
            if (duplicateRule == null) {
                continue;
            }
            duplicateRule.duplicate(taskInfo);
        }
    }

    /**
     * 组装con fig
     *
     * @param rule 一个json
     * @return 组装好的config
     */
    private BaseDuplicateRuleConfig assembleConfig(String rule) {
        //获取类
        JSONObject jsonObject = JSON.parseObject(rule);
        Integer ruleCode = (Integer) jsonObject.get(Params.RULE.getContent());
        if (ruleCode == null) {
            return null;
        }
        //生成对应规则类
        BaseDuplicateRuleConfig ruleConfig = ReflectUtil.newInstance(DuplicateRuleEnum.getConfigClazz(ruleCode));
        //注入属性
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            ReflectUtil.setFieldValue(ruleConfig, entry.getKey(), entry.getValue());
        }
        return ruleConfig;
    }
}
