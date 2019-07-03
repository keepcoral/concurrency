package com.bujidao.concurrency.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolExample3 {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newSingleThreadExecutor();

        for(int i=1;i<=10;i++){
            final int c=i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    log.info("{}",c);
                }
            });
        }

        executorService.shutdown();
    }
}
