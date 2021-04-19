package com.bin.framework.filtter;

import com.alibaba.fastjson.JSON;
import com.bin.common.Result;
import com.bin.common.enums.ResultEnum;
import com.bin.common.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.bin.common.constant.LoginConstant.BEARER;
import static com.bin.common.constant.RedisConstant.TOKEN_PREFIX;


/**
 * 确保在一次请求只通过一次filter，而不需要重复执行
 * 请求首先到达
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Value("${token.expireTime}")
    private int expireTime;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisUtil redisUtil;

    //如果没有Authorization直接放行
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        response.setContentType("application/json;charset=utf-8");
        if (!StringUtil.isEmpty(authHeader) && authHeader.startsWith(BEARER)) {
            String authToken = authHeader.substring(7);
            // 判断token是否正确
            if (redisUtil.hasKey(TOKEN_PREFIX + authToken)) {
                // 判断redis是否有保存
                String expireTime = redisUtil.hget(TOKEN_PREFIX + authToken, "expireTime").toString();
                //判断是否已过期
                if (JwtTokenUtil.isExpiration(expireTime)) {
                    //获得redis中用户的token刷新时效
                    String refreshTime = (String) redisUtil.getTokenRefreshTime(TOKEN_PREFIX + authToken);
                    String currentTime = DateUtil.getTime();
                    String id = JwtTokenUtil.parseToken(authToken);
                /*    String id = null;
                    try {
                        id = JwtUtil.parseJWT(authToken).getSubject();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/

                    if (DateUtil.compareDate(currentTime, refreshTime)) {
                        // 超过有效期，不予刷新
                        log.info("{}已超过有效期，不予刷新", authToken);
                        // token过期并不在刷新时间内，返回: 登录已失效
                        response.setHeader("Access-Control-Allow-Origin", "*");
                        response.getWriter().write(JSON.toJSONString(Result.error(ResultEnum.LOGIN_OVERDUE)));
                        // 删除过期token
                        redisUtil.del(TOKEN_PREFIX + authToken);
                        return;
                    } else {
                        // 仍在刷新时间内，则刷新token，放入请求头中
                        Map<String, Object> map = new HashMap<>(16);
                        map.put("id", id);
                        String jwtToken = JwtTokenUtil.generateToken(id, this.expireTime, map);
                        // 更新token
                        redisUtil.setTokenRefresh(jwtToken);
                        log.info("redis更新token: 旧token: {}, 新token: {}", authToken, jwtToken);
                        // 将token放入header返回，Access-Control-Expose-Headers解决自定义请求头前端获取不到的问题
                        response.setHeader("Access-Control-Expose-Headers", "Authorization");
                        response.setHeader("Authorization", BEARER + jwtToken);
                        // 删除过期token
                        redisUtil.del(TOKEN_PREFIX + authToken);
                    }
                }
            }
            String username = JwtTokenUtil.parseToken(authToken);
         /*   String username = null;
            try {
                username = JwtUtil.parseJWT(authToken).getSubject();
            } catch (Exception e) {
                e.printStackTrace();
            }*/
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }else {
                // 防止获取不到用户id，而引发的NPE
                response.setHeader("Access-Control-Allow-Origin", "*");
                response.getWriter().write(JSON.toJSONString(Result.error(ResultEnum.LOGIN_OVERDUE)));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
