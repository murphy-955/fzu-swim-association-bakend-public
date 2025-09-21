package com.fzuswimassociation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验运动员报名数量是否超过限制的注解
 *
 * @author 李泽聿
 * @since 2025-09-13 15:11
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAthleteLimit {
}
