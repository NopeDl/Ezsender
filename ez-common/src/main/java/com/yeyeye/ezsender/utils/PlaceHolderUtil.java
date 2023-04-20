package com.yeyeye.ezsender.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 占位符处理工具类
 *
 * @author yeyeye
 * @Date 2023/4/17 23:37
 */
public class PlaceHolderUtil {
    public static final String DEFAULT_REGEX = "\\$\\{[^}]+}";
    public static final Pattern DEFAULT_PATTEN = Pattern.compile(DEFAULT_REGEX);

    public static List<String> getParams(String content) {
        if (content == null || "".equals(content)) {
            return null;
        }
        List<String> res = new ArrayList<>();
        Matcher matcher = DEFAULT_PATTEN.matcher(content);
        while (matcher.find()) {
            String group = matcher.group();
            res.add(group.substring(2, group.length() - 1));
        }
        return res;
    }
}
