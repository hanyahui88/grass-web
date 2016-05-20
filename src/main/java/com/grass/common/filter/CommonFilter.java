package com.grass.common.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 韩亚辉 on 2016/5/20.
 */
public class CommonFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //  HttpServletRequest request = (HttpServletRequest) servletRequest;
        //  HttpSession session = request.getSession();
        // 指定允许其他域名访问
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        // 响应头设置
        response.addHeader("Access-Control-Allow-Headers", "POWERED-BY-FANTONG");
        response.addHeader("Access-Control-Max-Age", "30");
        // 需要过滤的代码
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
