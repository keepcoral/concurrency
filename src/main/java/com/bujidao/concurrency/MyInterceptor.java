package com.bujidao.concurrency;

import com.bujidao.concurrency.threadlocal.RequestHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器拦截");
        return true;
    }

    //这个方法无论是抛出异常还是正常进行都会执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //清除当前线程
        System.out.println("拦截器执行后");
        System.out.println("清除前user:"+RequestHolder.getUser());
        RequestHolder.remove();
        System.out.println("清除后user:"+RequestHolder.getUser());
    }
}
