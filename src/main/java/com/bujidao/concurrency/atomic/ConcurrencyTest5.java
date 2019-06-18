package com.bujidao.concurrency.atomic;

import com.bujidao.concurrency.annotation.ThreadSafe;
import lombok.Getter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@ThreadSafe//标记该类安全
public class ConcurrencyTest5 {
    //核心作用就是：更新指定的一个类的某个字段的值，而这个字段必须用volatile来修饰，且不能为static
    private static AtomicIntegerFieldUpdater<ConcurrencyTest5>
            //第一个是泛型的class，第二个是类的字段名称，这个字段必须用volatile来修饰，且不能为static
            updater=AtomicIntegerFieldUpdater.newUpdater(ConcurrencyTest5.class,"count");


    private volatile int count=100;

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        ConcurrencyTest5 concurrencyTest5= new ConcurrencyTest5();
        if(updater.compareAndSet(concurrencyTest5,100,200)){
            System.out.println("更新成功1："+concurrencyTest5.getCount());
        }

        if(updater.compareAndSet(concurrencyTest5,100,222)){
            System.out.println("更新成功2："+concurrencyTest5.getCount());
        }else{
            System.out.println("更新失败："+concurrencyTest5.getCount());
        }

    }


}
