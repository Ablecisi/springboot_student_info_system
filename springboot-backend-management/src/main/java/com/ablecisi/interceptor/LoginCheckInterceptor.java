package com.ablecisi.interceptor;

import com.ablecisi.entity.Result;
import com.ablecisi.util.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * itheima_web_project
 * com.ablecisi.interceptor
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/5/13
 * 星期一
 * 下午4:53
 */
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        log.info("url:{}", url);
        if (url.contains("login")) {
            return true;
        }
        String jwt = request.getHeader("token");
        if(! StringUtils.hasLength(jwt)) {
            log.info("请求头中没有token");
            Result error = Result.error("NOT_LOGIN");
            // 手动转换为json---> 使用fastjson
            String notLogin = JSONObject.toJSON(error).toString();
            response.getWriter().write(notLogin);
            return false;
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
            return false;
        }

        // 6.放行
        log.info("令牌合法");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
