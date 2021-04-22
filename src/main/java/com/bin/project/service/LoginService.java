package com.bin.project.service;


import com.bin.framework.sercurity.UserInfo;
import com.bin.project.pojo.param.LoginParam;
import com.bin.project.pojo.param.RegisterParam;

/**
 * @description 登录业务接口
 *
 * @author Administrator
 * @date 2020/9/22 3:47 下午
 */
public interface LoginService {
        /**
         * 登录
         * @param loginParam 登录信息
         * @return 用户信息
         */
        UserInfo login(LoginParam loginParam);


        /**
         * 注册
         * @param registerParam 注册信息
         * @return 用户信息
         */
        UserInfo register(RegisterParam registerParam);


}
