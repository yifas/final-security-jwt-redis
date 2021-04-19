package com.bin.project.controller;

import com.bin.common.Result;
import com.bin.framework.sercurity.UserInfo;
import com.bin.project.pojo.param.LoginParam;
import com.bin.project.service.LoginService;
import com.bin.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //@PreAuthorize("hasRole(ROLE_USER)")
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test() {
        return "权限控制";
    }


    @RequestMapping(value = "/findUserList",method = RequestMethod.GET)
    public Result findUserList() {
        return new Result(200,"查询成功",userService.findUserList());
    }
}
