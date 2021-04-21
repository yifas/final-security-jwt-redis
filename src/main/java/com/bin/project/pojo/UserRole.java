package com.bin.project.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 用户角色关系表
 * @author BEJSON
 * @date 2021-04-20
 */
@Data
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 角色id
     */
    private Long roleId;

    public UserRole() {}
}