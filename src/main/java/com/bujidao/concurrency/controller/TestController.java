package com.bujidao.concurrency.controller;

import com.bujidao.concurrency.threadlocal.RequestHolder;
import com.bujidao.concurrency.threadlocal.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @RequestMapping("/putuser")
    public User putuser(HttpServletRequest request){
        User user=new User();
        user.setId("200");
        user.setName("bujidao");
        request.getSession().setAttribute("user",user);
        return user;
    }

    @RequestMapping("/threadlocal/get")
    public User getuser(HttpServletRequest request){
        System.out.println("执行了get方法");
        return RequestHolder.getUser();
    }
}

//do filter:25
//threadLocalId:bujidao
//拦截器拦截
//执行了get方法
//拦截器执行后
//清除前user:com.bujidao.concurrency.threadlocal.User@2027d25b
//清除后user:null
