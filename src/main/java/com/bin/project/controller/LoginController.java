package com.bin.project.controller;

import cn.hutool.core.util.IdUtil;
import com.bin.common.Result;
import com.bin.common.util.JwtTokenUtil;
import com.bin.common.util.JwtUtil;
import com.bin.common.util.RedisUtil;
import com.bin.framework.sercurity.UserInfo;
import com.bin.project.pojo.param.LoginParam;
import com.bin.project.pojo.param.RegisterParam;
import com.bin.project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.bin.common.constant.LoginConstant.BEARER;
import static com.bin.common.constant.LoginConstant.SUCCESS;

/**
 * @description 登录
 *
 * @author Administrator
 * @date 2020/9/22 3:30 下午
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${token.expireTime}")
    private int expireTime;

  /*  @Autowired
    private RabbitTemplate rabbitTemplate;*/

    /**
     * 登录
     * @param loginParam 登录信息
     * @param request /
     * @param response /
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody @Validated LoginParam loginParam,
                        HttpServletRequest request, HttpServletResponse response) {
        UserInfo userInfo = loginService.login(loginParam);
        return getLoginResult(request, response, userInfo);
    }




    /**
     * 注册
     * @param registerParam 注册信息
     * @param request /
     * @param response /
     */
/*
    @PostMapping("/register")
    public Result register(@RequestBody @Validated RegisterParam registerParam,
                           HttpServletRequest request, HttpServletResponse response) {
        UserInfo userInfo = loginService.register(registerParam);
        Map<String, String> userMap = new HashMap<>(2);
        userMap.put("username", userInfo.getUsername());
        userMap.put("password", userInfo.getPassword());
        //rabbitTemplate.convertAndSend(MessageConstant.DAI_FA_ACCOUNT_EXCHANGE, MessageConstant.DAI_FA_ACCOUNT_KEY, userMap);
        return getLoginResult(request, response, userInfo);
    }
*/


    /**
     * 公共方法抽取
     * @param request
     * @param response
     * @return
     */
    private Result getLoginResult(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("id", String.valueOf(userInfo.getId()));
        // 签发token
        //String jwtToken = JwtUtil.generateToken(userInfo.getUsername(), expireTime, map);
        String jwtToken = JwtTokenUtil.generateToken(userInfo.getUsername(), expireTime, map);
        //todo
        //将Token存储到redis中
        redisUtil.setTokenRefresh(jwtToken);
        // 将token放入header返回，Access-Control-Expose-Headers解决自定义请求头前端获取不到的问题
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        response.setHeader("Authorization", BEARER + jwtToken);
        return Result.success(SUCCESS, userInfo);
    }
}
