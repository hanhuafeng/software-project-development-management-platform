package com.example.software.project.development.management.platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * @Description 配置文件加载类
 *
 */
@Configuration
@PropertySource(value = {"classpath:/config.properties"}, ignoreResourceNotFound = true,encoding = "utf-8")
public class PropConfig {
	// PropertySourcesPlaceholderConfigurer这个bean，
    // 这个bean主要用于解决@value中使用的${…}占位符。
    // 假如你不使用${…}占位符的话，可以不使用这个bean。
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
