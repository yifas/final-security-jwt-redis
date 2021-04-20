package com.bin.framework.sercurity;

import com.alibaba.fastjson.JSON;
import com.bin.common.Result;
import com.bin.common.enums.ResultEnum;
import com.bin.common.util.ServletUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户未登录时返回给前端的数据JSON
 *
 * @author Administrator
 */
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException {
        ServletUtil.renderString(JSON.toJSONString(Result.error(ResultEnum.USER_NO_ACCESS)));
    }
}
