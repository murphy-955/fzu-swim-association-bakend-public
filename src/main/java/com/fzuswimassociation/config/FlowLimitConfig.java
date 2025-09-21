package com.fzuswimassociation.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 从yaml中读取流量限制配置
 *
 * @author 李泽聿
 * @since 2025-09-17 14:28
 */

@Configuration
@ConfigurationProperties(prefix = "flow-control")
@Data
public class FlowLimitConfig {
    private int maxRequest;
    private int timeWindow;
    private boolean whetherEnabledOrNot;
}
