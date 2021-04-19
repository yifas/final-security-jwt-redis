package com.bin.project.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SysUser implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态（0-正常，1-删除，2-禁用）
     */
    private String status;
}