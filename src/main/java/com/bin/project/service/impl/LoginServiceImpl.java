package com.bin.project.service.impl;

import com.bin.framework.sercurity.UserInfo;
import com.bin.project.pojo.param.LoginParam;
import com.bin.project.pojo.param.RegisterParam;
import com.bin.project.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.bin.common.constant.LoginConstant.FAILURE;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public UserInfo login(LoginParam loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        //是否走重写的逻辑?
        //UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UserInfo userInfo = (UserInfo) userDetailsService.loadUserByUsername(username);
        //checkRole(userInfo.getRole(), loginParam.getRole());
        String detailsPassword = userInfo.getPassword();
        //密码加密
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(password,detailsPassword)) {
            throw new RuntimeException(FAILURE);
        }
        return userInfo;
    }

    @Override
    public UserInfo register(RegisterParam registerParam) {
        return null;
    }
}
