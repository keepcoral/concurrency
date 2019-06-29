package com.bujidao.concurrency.singleton;

import com.bujidao.concurrency.annotation.NotRecommend;
import com.bujidao.concurrency.annotation.NotThreadSafe;
import com.bujidao.concurrency.annotation.ThreadSafe;

/**
 * 懒汉模式 -> 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe//并不是线程安全的！
public class SingletonExample4 {
    //构造方法私有化
    private SingletonExample4() {
        //这里会有资源的处理包括运算
    }

    /**
     * instance = new SingletonExample4();
     * 可拆分为3个步骤
     * 1.memory=allocate() 分配对象的空间
     * 2.ctorInstance() 初始化对象
     * 3.instance=memory 设置instance指向刚分配的内存
     * 以上步骤完成后，instance就指向了实际分配的内存地址
     *
     * 但是----------------------------------------
     * 在多线程的情况下，指令重排会(线程安全有序性)影响到程序的正确性
     * JVM和cpu优化，发生了指令重排 --- 将原本的123顺序变为了132顺序(2和3没有前后关系)
     * 1.memory=allocate() 分配对象的空间
     * 3.instance=memory 设置instance指向刚分配的内存
     * 2.ctorInstance() 初始化对象
     * 在这个前提下假设有线程A,B，如下：
     * 所以在线程B中，返回的是指向了内存空间但未初始化对象的instance
     * 因此该写法是线程不安全的！
     */


    //单例对象
    private static SingletonExample4 instance = null;

    //静态的工厂方法获取单例对象
    public static SingletonExample4 getInstance() {
        if (instance == null) { // -->线程B，由于线程A的操作，instance!=null但是实际上instance里面还未初始化对象
            //双重检测机制
            synchronized (SingletonExample4.class) {//同步锁
                if (instance == null) {
                    instance = new SingletonExample4();  // -->线程A-第3步，将instance指向分配的内存
                }
            }
        }
        return instance;
    }

}
