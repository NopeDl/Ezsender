package com.yeyeye.ezsender.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author yeyeye
 * @Date 2023/4/12 21:38
 */
@Configuration
@MapperScan("com.yeyeye.ezsender.mapper")
public class MapperConfig {
}
