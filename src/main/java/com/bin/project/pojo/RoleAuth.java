package com.bin.project.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 角色权限关系表
 * @author BEJSON
 * @date 2021-04-20
 */
@Data
public class RoleAuth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long authId;

    public RoleAuth() {}
}