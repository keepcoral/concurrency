package com.bujidao.concurrency.AQS;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {
    //设置初始值5,看有多少个线程需要同步等待
    private static CyclicBarrier cyclicBarrier=new CyclicBarrier(5);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(int i=1;i<=10;i++){
            final int c=i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        test(c);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
    }

    public static void test(int i) throws InterruptedException, BrokenBarrierException {
        Thread.sleep(1000);
        System.out.println(i+"--ready");
        cyclicBarrier.await();//当它到达初始值后，await后面的操作就可以执行了
        System.out.println(i+"--continue");
    }
}
