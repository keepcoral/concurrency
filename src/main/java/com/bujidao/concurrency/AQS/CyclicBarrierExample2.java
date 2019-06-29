package com.bujidao.concurrency.AQS;

import java.util.concurrent.*;

public class CyclicBarrierExample2 {
    //设置初始值5,看有多少个线程需要同步等待
    private static CyclicBarrier cyclicBarrier=new CyclicBarrier(5, new Runnable() {
        @Override
        public void run() {
            System.out.println("callback");
        }
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(int i=1;i<=10;i++){
            Thread.sleep(1000);
            final int c=i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        test(c);
                    } catch (Exception e) {
                        System.out.println(c+"抛出异常");
                    }
                }
            });
        }
        executorService.shutdown();
    }

    public static void test(int i) throws Exception {
        Thread.sleep(1000);
        System.out.println(i+"--ready");
        cyclicBarrier.await();
        System.out.println(i+"--continue");
    }
//1--ready
//2--ready
//3--ready
//4--ready
//5--ready
//callback
//5--continue
//1--continue
//3--continue
//2--continue
//4--continue
}
