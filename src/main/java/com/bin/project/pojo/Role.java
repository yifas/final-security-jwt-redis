package com.bin.project.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description 角色表
 * @author BEJSON
 * @date 2021-04-20
 */
@Data
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 角色id
     */
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    public Role() {}
}