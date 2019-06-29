package com.bujidao.concurrency.threadlocal;

public class RequestHolder {
    //ThreadLocal默认的key为线程名称
    private final static ThreadLocal<User> USER_THREAD_LOCAL=new ThreadLocal<>();

    //在请求进入后端，但没有实际处理请求的时候，把相关的内容写进去，这里使用Filter
    public static void add(User user){
        USER_THREAD_LOCAL.set(user);
    }

    public static User getUser(){
        return USER_THREAD_LOCAL.get();
    }

    //在实际处理完之后进行处理，interceptor之后
    public static void remove(){
        USER_THREAD_LOCAL.remove();
    }
}
