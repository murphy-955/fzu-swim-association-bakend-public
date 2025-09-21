package com.fzuswimassociation.handler;

import com.fzuswimassociation.config.FlowLimitConfig;
import com.fzuswimassociation.enm.StatusCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * 使用redis进行流量限制
 *
 * @author 李泽聿
 * @since 2025-09-17 14:04
 */

@Component
public class FlowLimitHandler implements HandlerInterceptor {
    @Qualifier("flow-control-com.fzuswimassociation.config.FlowLimitConfig")
    @Autowired
    private FlowLimitConfig flowLimit;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 流量控制拦截器
     * 限制每IP每分钟请求次数，超过限制则返回444状态码，及提示信息<br>
     * 可以在yaml中配置最大请求数，滑动窗口，是否启用流量限制参数，如：<br>
     * {@code flow-control.max-request: 100}<br>
     * {@code flow-control.time-window: 60}<br>
     * {@code flow-control.whether-enabled-or-not: true}<br>
     *
     * @param request 请求
     * @param response 响应
     * @param handler 处理器
     * @return true：继续执行，false：中断执行
     * @author 李泽聿
     * @since 2025-09-17 15:14
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!flowLimit.isWhetherEnabledOrNot()){
            return true;
        }
        // 获取流量限制相关参数
        int maxRequest = flowLimit.getMaxRequest();
        int timeWindow = flowLimit.getTimeWindow();
        // 获取IP地址
        String ip = request.getRemoteAddr();
        String key = "ip" + ip + ":requestCount";
        Long increment = redisTemplate.opsForValue().increment(key);
        // 打印IP地址和当前请求次数
        System.out.println("IP地址：" + ip);
        System.out.println("当前请求次数：" + increment);
        if (increment > maxRequest) {
            response.setStatus(StatusCodeEnum.REQUESTS_ARE_TOO_FREQUENT.getStatusCode());  // 设置HTTP状态码
            response.setContentType("application/json;charset=UTF-8");

            String json = """
                    {
                        "statusCode": %d,
                        "message": "%s",
                        "data": null
                    }
                    """.formatted(StatusCodeEnum.REQUESTS_ARE_TOO_FREQUENT.getStatusCode(),
                    StatusCodeEnum.REQUESTS_ARE_TOO_FREQUENT.getMessage());
            PrintWriter writer = response.getWriter();
            writer.print(json);
            writer.flush();
            return false;
        } else {
            redisTemplate.expire(key, timeWindow, TimeUnit.SECONDS);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
}
