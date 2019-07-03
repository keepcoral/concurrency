package com.bujidao.concurrency.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService=Executors.newScheduledThreadPool(5);
        scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("run");
            }
        },  3,TimeUnit.SECONDS);//延迟3s执行

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("run22");
            }
        },  1,3,TimeUnit.SECONDS);//每隔3s执行一次
//        scheduledExecutorService.shutdown();
    }
}
