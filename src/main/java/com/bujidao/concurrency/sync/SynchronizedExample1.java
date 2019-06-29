package com.bujidao.concurrency.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedExample1 {

    //修饰一个代码块
    public void test1(int j){
        synchronized (this){
            for(int i=1;i<=10;i++){
                System.out.println("test1 j="+j+","+"i="+i);
            }
        }
    }

    //修饰一个方法
    public synchronized void test2(int j){
        for(int i=1;i<=100;i++){
            System.out.println("test2  j="+j+","+"i="+i);
        }
    }

    //修饰一个方法
    public synchronized void test3(int j){
        for(int i=1;i<=100;i++){
            System.out.println("test3  j="+j+","+"i="+i);
        }
    }

    //作用于调用对象
    //两个不同对象调用[修饰块]的test1()，同步代码块是作用于当前对象，那么不同调用对象之间相互不影响，交替执行
    public static void main(String[] args) {
        SynchronizedExample1 example1=new SynchronizedExample1();
        SynchronizedExample1 example2=new SynchronizedExample1();
        ExecutorService executorService= Executors.newCachedThreadPool();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example1.test3(3);
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example1.test2(2);
            }
        });

        executorService.shutdown();
    }
}
