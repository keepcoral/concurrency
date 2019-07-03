package com.bujidao.concurrency.AQS.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureTaskExample {
    public static void main(String[] args) throws Exception {
        FutureTask<String> stringFutureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("do something");
                Thread.sleep(5000);
                return "Done";
            }
        });
        new Thread(stringFutureTask).start();
        //这里必须使用start方法
        //这里如果使用run方法，那么就是依旧在主线程
        //并没有开辟另外一个线程执行callable的方法
        Thread.sleep(1000);
        log.info("do something in main");
        log.info("result: {}",stringFutureTask.get());
    }

}
