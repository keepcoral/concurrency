package com.bujidao.concurrency.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TestController {

    @RequestMapping("test")
    public String test1(){
        return "this is test";
    }

    @RequestMapping("test2")
    public Map<String,String> test2(HttpServletRequest request){
        Map<String ,String> modelMap=new HashMap<>();
        modelMap.put("test2","this is test2");
        modelMap.put("a",request.getParameter("a"));
        return modelMap;
    }
}
