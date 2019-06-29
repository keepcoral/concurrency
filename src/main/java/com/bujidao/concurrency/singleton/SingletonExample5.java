package com.bujidao.concurrency.singleton;

import com.bujidao.concurrency.annotation.NotThreadSafe;
import com.bujidao.concurrency.annotation.ThreadSafe;

/**
 * 懒汉模式 -> 双重同步锁单例模式
 * 单例实例在第一次使用时进行创建
 */
@ThreadSafe
public class SingletonExample5 {
    //构造方法私有化
    private SingletonExample5() {
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
     * 加上了volatile关键字，可以让它不发生指令重排
     */


    //单例对象,加上volatile关键字，可以不让它发生指令重排
    //volatile+双重检测机制->禁止指令重排
    //这里涉及到了volatile的写操作
    private volatile static SingletonExample5 instance = null;

    //静态的工厂方法获取单例对象
    public static SingletonExample5 getInstance() {
        if (instance == null) { //
            //双重检测机制
            synchronized (SingletonExample5.class) {//同步锁
                if (instance == null) {
                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }

}
