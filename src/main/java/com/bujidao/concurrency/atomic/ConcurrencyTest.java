package com.bujidao.concurrency.atomic;

import com.bujidao.concurrency.annotation.NotThreadSafe;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j
@NotThreadSafe//标记该类不安全
public class ConcurrencyTest {
    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal=200;

    //计数
    public static int count = 0;

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
                        add();
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
        System.out.println(count);
    }

    public static void add(){
        count++;
    }
}
