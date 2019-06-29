package com.bujidao.concurrency.AQS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample1 {
    private static int threadNum=200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch=new CountDownLatch(threadNum);
        for(int i=0;i<=threadNum-1;i++){
            final int c=i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        test(c);
                    }catch (Exception e){
                        System.out.println("yc");
                    }finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        //执行完200个请求才输出这句话
        System.out.println("200请求处理完");
        executorService.shutdown();
    }

    public static void test(int i){
        try {
            Thread.sleep(100);
            System.out.println(i);
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
