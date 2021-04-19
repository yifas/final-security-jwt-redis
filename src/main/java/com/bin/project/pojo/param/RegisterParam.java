package com.bin.project.pojo.param;

import lombok.Data;


/**
 * @description 注册信息
 *
 * @author Administrator
 * @date 2020/10/19 11:58
 */
@Data
public class RegisterParam {
    //@NotBlank(message = "用户名不能为空")
    private String username;

    //@NotBlank(message = "密码不能为空")
    private String password;

    //@NotBlank(message = "验证码不能为空")
    private String verificationCode;
}
