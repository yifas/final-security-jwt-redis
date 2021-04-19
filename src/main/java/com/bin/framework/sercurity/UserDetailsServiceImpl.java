package com.bin.framework.sercurity;

import com.bin.common.constant.LoginConstant;
import com.bin.project.dao.UserDao;
import com.bin.project.pojo.SysUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 用于实现自定义的登录逻辑
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //从数据库查询用户名是否存在
        SysUser result = userDao.findByUsername(username.trim());
        if (result==null){
            throw new UsernameNotFoundException(LoginConstant.USER_DOES_NOT_EXIST);
        }else {
            //查询用户名对应的所有role
            List<String> roleList = userDao.findRoleByUsername(username.trim());
            /*
            List<GrantedAuthority> list = new ArrayList<>();
            for (String s : roleList) {
                list.add(new SimpleGrantedAuthority("ROLE_"+s));
                //list.add(new SimpleGrantedAuthority(s));
            }
            //交给框架比较密码 org.springframework.security.core.userdetails.User;
            return new User(result.getUsername(),result.getPassword(),list);*/
            UserInfo userInfo = new UserInfo();
            BeanUtils.copyProperties(result, userInfo);
            for (String s : roleList) {
                userInfo.setAuthorities(Collections.singleton(new SimpleGrantedAuthority("ROLE_"+s)));
            }
            return userInfo;
        }
    }
}
