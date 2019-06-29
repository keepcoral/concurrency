package com.bujidao.concurrency.AQS;




import ch.qos.logback.core.net.SyslogOutputStream;
import ch.qos.logback.core.util.TimeUtil;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample2 {
    private static int threadNum=200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch=new CountDownLatch(threadNum);
        for(int i=0;i<=threadNum-1;i++){
            //Thread.sleep(10); 如果放在这里根本没有用
            final int c=i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        test(c);
                    }catch (Exception e){
                        System.out.println("yc");
                    }finally {
                        System.out.println("c=="+c);
                        countDownLatch.countDown();
                    }
                }
            });
        }
        System.out.println("这里等待");
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        //执行完200个请求才输出这句话
        System.out.println("200请求处理完");
        executorService.shutdown();
    }

    public static void test(int i){
        try {
            Thread.sleep(100);
            System.out.println(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
