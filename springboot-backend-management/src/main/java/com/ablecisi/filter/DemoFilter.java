package com.ablecisi.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * itheima_web_project
 * com.ablecisi.filter
 *
 * @author Ablecisi
 * @version 1.0
 * 2024/5/12
 * 星期日
 * 下午10:48
 */
@Deprecated
//@WebFilter(urlPatterns = "/*")
public class DemoFilter implements Filter {
    @Override // 初始化方法,只会执行一次
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override // 拦截方法, 每次请求都会执行
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器拦截到了请求");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override // 销毁方法,只会执行一次
    public void destroy() {
        Filter.super.destroy();
    }
}
