package com.bin.common.util;


import com.bin.framework.sercurity.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

/**
 * @author Administrator
 */
public class TokenDecode {
    /**
     * 获取用户信息
     *
     * @return
     */
    public static UserInfo getUserInfo() {
        UserInfo userInfo = null;
        // 获取用户认证信息对象。
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 认证信息可能为空，因此需要进行判断。
        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserInfo) {
                userInfo = (UserInfo) principal;
            }
        }
        return userInfo;
    }
}
