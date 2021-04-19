package com.bin.project.controller;

import com.bin.common.Result;
import com.bin.framework.sercurity.UserInfo;
import com.bin.project.pojo.param.LoginParam;
import com.bin.project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {


    @RequestMapping("/test")
    public String test() {
        return "权限控制";
    }


}
