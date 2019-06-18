package com.bujidao.concurrency.atomic;

import com.bujidao.concurrency.annotation.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.atomic.LongAdder;

@ThreadSafe//标记该类安全
public class ConcurrencyTest6 {
    //请求总数
    public static int clientTotal = 50000;

    //同时并发执行的线程数
    public static int threadTotal=500;

    //计数
    public static AtomicBoolean isHappen = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        //创建一个线程池
        ExecutorService executorService= Executors.newCachedThreadPool();

        //定义一个信号量,设置执行线程的并发数
        final Semaphore semaphore=new Semaphore(threadTotal);

        //传入请求总数，在请求完之后再统计结果
        final CountDownLatch countDownLatch=new CountDownLatch(clientTotal);

        for(int i=0;i<clientTotal;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        //wait操作
                        semaphore.acquire();
                        //进行业务操作
                        test();
                        //signal操作
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //每执行完一个线程，它里面的clientTotal计数值就会-1
                    countDownLatch.countDown();
                }
            });
        }
        //await这个方法可以保证clientTotal必须为0，也就是所有的线程都必须执行完
        countDownLatch.await();
        executorService.shutdown();
        long et=System.currentTimeMillis();
    }

    public static void test(){
        if(isHappen.compareAndSet(false,true)){
            System.out.println("当前布尔值是："+isHappen);
        }
    }
}
