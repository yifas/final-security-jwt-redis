package com.bin.project.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 权限表
 * @author BEJSON
 * @date 2021-04-20
 */
@Data
public class Auth implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限标识
     */
    private String permission;

    public Auth() {}
}