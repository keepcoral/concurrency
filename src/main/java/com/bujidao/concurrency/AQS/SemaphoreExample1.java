package com.bujidao.concurrency.AQS;

import java.util.Date;
import java.util.concurrent.*;

public class SemaphoreExample1 {
    private static int threadNum=20;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService= Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch=new CountDownLatch(threadNum);
        final Semaphore semaphore=new Semaphore(3);
        for(int i=0;i<=threadNum-1;i++){
            final int c=i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
//                        if(semaphore.tryAcquire(2,TimeUnit.SECONDS)){//尝试拿到许可，如果拿不到先等待2s，没获取到直接丢弃
//                            test(c);
//                            semaphore.release();
//                        }
                        if(semaphore.tryAcquire()){//尝试拿到许可，如果拿不到直接丢弃掉
                            test(c);
                            semaphore.release();
                        }
                    }catch (Exception e){
                        System.out.println("yc");
                    }finally {
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    public static void test(int i) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println(new Date()+"--i="+i);
    }
}