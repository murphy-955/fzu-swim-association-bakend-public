package com.fzuswimassociation.config;

import com.fzuswimassociation.handler.FlowLimitHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置Mvc拦截器
 *
 * @author 李泽聿
 * @since 2025-09-17 14:18
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private FlowLimitHandler flowHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(flowHandler)
                .addPathPatterns("/**");
    }
}
