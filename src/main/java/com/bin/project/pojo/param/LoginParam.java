package com.bin.project.pojo.param;

import lombok.Data;


import java.io.Serializable;

/**
 * @description 用户登录
 *
 * @author Administrator
 * @date 2020/9/22 3:37 下午
 */
@Data
public class LoginParam implements Serializable {

    //@NotBlank(message = "用户名不能为空")
    private String username;

    //@NotBlank(message = "密码不能为空")
    private String password;

   // @NotNull(message = "请选择登录类型")
    //private Integer role;
}
