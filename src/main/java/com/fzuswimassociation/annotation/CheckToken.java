package com.fzuswimassociation.annotation;

import java.lang.annotation.*;

/**
 * 校验Token注解<br>
 * 注意，校验Token时，参数列表的第一个参数必须是包含了Token的实体类
 *
 * @author 李泽聿
 * @since 2025-09-07 14:44
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckToken {
}
