package com.bujidao.concurrency.syncContainer;

import com.bujidao.concurrency.annotation.NotThreadSafe;
import com.bujidao.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe//标记该类不安全
public class VectorTest2 {

    private static Vector<Integer> vector=new Vector<>();

    public static void main(String[] args) {
        while(true){
            for(int i=0;i<=10;i++){
                vector.add(i);
            }
            //vector虽然能保证同一个时刻只有一个线程访问它
            //但是for(int i=0;i<=vector.size()-1;i++)有可能同时取到vector.size()
            //然后对它进行remove操作，最终导致数组越界
            Thread thread1=new Thread(){
                public void run(){
                    for(int i=0;i<=vector.size()-1;i++){
                        vector.remove(i);
                    }
                }
            };

            Thread thread2=new Thread(){
                public void run(){
                    for(int i=0;i<=vector.size()-1;i++){
                        //get方法的异常由于remove方法引起
                        vector.get(i);
                    }
                }
            };
            thread1.start();
            thread2.start();
        }
    }
}
