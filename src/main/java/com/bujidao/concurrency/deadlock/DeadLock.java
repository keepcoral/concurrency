package com.bujidao.concurrency.deadlock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadLock implements Runnable{
    public int flag=1;
    //静态是所有对象对象共享的
    public static Object o1=new Object();
    public static Object o2=new Object();
    @Override
    public void run() {
        log.info("flag=={}",flag);
        if(flag==1){
            synchronized (o1){
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    log.error("{}",e);
                }
                synchronized (o2){
                    log.info("o2");
                }
            }
        }

        if(flag==0){
            synchronized (o2){
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    log.error("{}",e);
                }
                synchronized (o1){
                    log.info("o1");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock deadLock2=new DeadLock();
        DeadLock deadLock1=new DeadLock();
        deadLock1.flag=1;
        deadLock2.flag=0;
        new Thread(deadLock1).start();
        new Thread(deadLock2).start();
    }
}
