package com.bujidao.concurrency.singleton;

import com.bujidao.concurrency.annotation.Recommend;
import com.bujidao.concurrency.annotation.ThreadSafe;

/**
 * 枚举模式：最安全
 * 相比饿汉模式，是在调用的时候才初始化
 * 相比懒汉模式，安全性得到保障
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    //构造方法私有化
    private SingletonExample7() {
        //这里会有资源的处理包括运算
    }

    public static SingletonExample7 getInstance(){
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        //JVM保证这个方法绝对只调用一次
        Singleton(){
            singleton=new SingletonExample7();
        }

        public SingletonExample7 getInstance(){
            return singleton;
        }
    }
}
