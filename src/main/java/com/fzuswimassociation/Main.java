package com.fzuswimassociation;

import com.fzuswimassociation.config.FlowLimitConfig;
import com.fzuswimassociation.config.MyThreadPoolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// 读取流量配置信息
@EnableConfigurationProperties({FlowLimitConfig.class,
        MyThreadPoolConfig.class})
// 开启定时任务
@EnableScheduling
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}