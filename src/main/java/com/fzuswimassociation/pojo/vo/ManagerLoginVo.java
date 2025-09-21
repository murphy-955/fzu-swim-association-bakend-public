package com.fzuswimassociation.pojo.vo;

import lombok.Data;

/**
 * 管理员登录前端实体类<br>
 * {@code {
 *   "username": "名为用户名",
 *   "password": "MD5加密后的密码"
 * }
 * }
 *
 * @author 李泽聿
 * @since 2025-09-01 19:21
 */

@Data
public class ManagerLoginVo {
    private String userName;
    private String password;
}
