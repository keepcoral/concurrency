package com.bujidao.concurrency.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedExample2 {

    //修饰一个类
    public static void test1(int j){
        synchronized (SynchronizedExample2.class){
            for(int i=1;i<=10;i++){
                System.out.println("test1 j="+j+","+"i="+i);
            }
        }
    }

    //修饰一个静态方法，作用对象是这个类所有对象
    public synchronized static void test2(int j){
        for(int i=1;i<=10;i++){
            System.out.println("test2  j="+j+","+"i="+i);
        }
    }

    //作用于调用对象
    //两个不同对象调用[被修饰的静态方法]的test2()，同一个时间只有一线程可以执行，先执行完一个方法，再执行另一个方法
    public static void main(String[] args) {
        SynchronizedExample2 example1=new SynchronizedExample2();
        SynchronizedExample2 example2=new SynchronizedExample2();
        ExecutorService executorService= Executors.newCachedThreadPool();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                example1.test1(1);
            }
        });

        executorService.execute (new Runnable() {
            @Override
            public void run() {
                example2.test1(2);
            }
        });

        executorService.shutdown();
    }
}
