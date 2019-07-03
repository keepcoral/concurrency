package com.bujidao.concurrency.AQS.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureExample {
    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("do something");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService= Executors.newCachedThreadPool();
        Future<String> future=executorService.submit(new MyCallable());
        try {
            log.info("do something in main");
            Thread.sleep(1000);
            log.info("result {}",future.get());//会阻塞主线程，直到callable线程得到返回值
        } catch (Exception e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }

}
