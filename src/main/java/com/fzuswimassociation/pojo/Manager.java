package com.fzuswimassociation.pojo;

import lombok.Data;

/**
 * 管理员实体类
 *
 * @author 李泽聿
 * @since 2025-09-01 20:10
 */

@Data
public class Manager {
    private Long id;
    private String userName;
    private String password;
}
