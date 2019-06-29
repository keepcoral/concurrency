package com.bujidao.concurrency.singleton;

import com.bujidao.concurrency.annotation.NotThreadSafe;
import com.bujidao.concurrency.annotation.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载的时候进行创建
 * 缺点：如果构造函数中存在过多处理，可能会引起性能问题，容易造成资源浪费
 */
@ThreadSafe
public class SingletonExample1 {
    //构造方法私有化
    private SingletonExample1() {
        //这里会有资源的处理包括运算
    }

    //单例对象
    private static SingletonExample1 instance = new SingletonExample1();

    //静态的工厂方法获取单例对象
    public static SingletonExample1 getInstance() {
        return instance;
    }

}
