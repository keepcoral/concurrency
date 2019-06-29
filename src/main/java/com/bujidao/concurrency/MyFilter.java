package com.bujidao.concurrency;

import com.bujidao.concurrency.threadlocal.RequestHolder;
import com.bujidao.concurrency.threadlocal.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        User user= (User) request.getSession().getAttribute("user");
        RequestHolder.add(user);
        System.out.println("do filter:"+Thread.currentThread().getId()+"---"+request.getContextPath());
        System.out.println("threadLocalId:"+ RequestHolder.getUser().getName());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
