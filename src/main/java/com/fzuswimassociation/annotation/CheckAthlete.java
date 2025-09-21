package com.fzuswimassociation.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 校验运动员姓名，学号；学院；报名项目是否合法；报名是否已经截至
 *
 * @author 李泽聿
 * @since 2025-09-10 14:24
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAthlete {
}
