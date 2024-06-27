package com.ablecisi.filter;

import com.ablecisi.entity.Result;
import com.ablecisi.util.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * itheima_web_project
 * com.ablecisi.filter
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/5/12
 * 星期日
 * 下午11:20
 */
@Slf4j
@Deprecated
//@WebFilter("/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String url = request.getRequestURI();
        log.info("url:{}", url);
        if (url.contains("login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String jwt = request.getHeader("token");
        if(!StringUtils.hasLength(jwt)) {
            log.info("请求头中没有token");
            Result error = Result.error("NOT_LOGIN");
            // 手动转换为json---> 使用fastjson
            String notLogin = JSONObject.toJSON(error).toString();
            response.getWriter().write(notLogin);
            return;
        }

        // 5.解析token
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            log.info("token解析失败");
            Result error = Result.error("NOT_LOGIN");
            // 手动转换为json---> 使用fastjson
            String notLogin = JSONObject.toJSON(error).toString();
            response.getWriter().write(notLogin);
            return;
        }

        // 6.放行
        log.info("令牌合法");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
