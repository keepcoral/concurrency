package com.bujidao.concurrency.singleton;

import com.bujidao.concurrency.annotation.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample2 {
    //构造方法私有化
    private SingletonExample2() {
        //这里会有资源的处理包括运算
    }

    //单例对象
    private static SingletonExample2 instance = null;

    //静态的工厂方法获取单例对象
    public static SingletonExample2 getInstance() {
        //这里会出现线程安全问题
        if (instance == null) {
            instance = new SingletonExample2();
        }
        return instance;
    }

}
