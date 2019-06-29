package com.bujidao.concurrency.singleton;

import com.bujidao.concurrency.annotation.NotRecommend;
import com.bujidao.concurrency.annotation.NotThreadSafe;
import com.bujidao.concurrency.annotation.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 */
@ThreadSafe
@NotRecommend//不推荐，因为synchronized带来了性能上的开销
public class SingletonExample3 {
    //构造方法私有化
    private SingletonExample3() {
        //这里会有资源的处理包括运算
    }

    //单例对象
    private static SingletonExample3 instance = null;

    //静态的工厂方法获取单例对象
    //将给方法添加synchronized，在同一时间内只有一个线程可以访问
    //不推荐
    public static synchronized SingletonExample3 getInstance() {
        //这里会出现线程安全问题
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }

}
