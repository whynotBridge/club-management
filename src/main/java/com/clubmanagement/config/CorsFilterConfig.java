package com.clubmanagement.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", filterName = "CORSFilter")
@Order(1) // 数值越小，优先级越高，因此这个过滤器会先执行
@Slf4j
public class CorsFilterConfig implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        log.info("CorsFilterConfig doFilter 。。。");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 获取请求的源（前端域名）
        String origin = request.getHeader("Origin");
        if (origin == null) {
            origin = request.getHeader("Referer");
        }

        // 允许特定的前端域名进行跨域请求
//        response.setHeader("Access-Control-Allow-Origin", "http://your-frontend-domain.com"); // // 替换为实际前端域名
        response.setHeader("Access-Control-Allow-Origin", "*"); // 替换为实际前端域名
        // 允许跨域请求携带cookie
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 允许预检请求的特定HTTP方法
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        // 允许预检请求的特定头信息
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // 检查是否为预检请求（OPTIONS）
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            // 对于预检请求，直接返回响应，不继续过滤器链
            return;
        }

        // 对于实际的请求，继续过滤器链
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}