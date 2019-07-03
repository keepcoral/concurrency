package com.bujidao.concurrency.lock;

import com.bujidao.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

@Slf4j
//Condition适用于某个线程一起等待某个条件
public class LockTest5 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock=new ReentrantLock();
        Condition condition=reentrantLock.newCondition();

        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();//线程就加入到了AQS的等待队列中
                try{
                    System.out.println("wait signal");//1
                    condition.await();//从AQS队列移除，对应的操作应该是锁的释放
                    //然后加入到condition的等待队列，等待该线程需要一个信号
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println("get signal");//4
                reentrantLock.unlock();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();//线程2因为线程1释放锁的关系被唤醒
                //线程2获取锁，加入AQS等待队列中
                System.out.println("get lock");//2
                try{
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                condition.signalAll();//发送信号
                //这时候condition等待队列中有一个线程1的节点，于是取出节点加入AQS队列中
                //这里线程1并没有被唤醒，只是加入队列而已
                System.out.println("send signal");//3
                reentrantLock.unlock();//释放锁，AQS中只剩下线程1，线程1被唤醒
            }
        }).start();
    }
}
