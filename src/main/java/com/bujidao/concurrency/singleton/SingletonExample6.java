package com.bujidao.concurrency.singleton;

import com.bujidao.concurrency.annotation.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载的时候进行创建
 * 缺点：如果构造函数中存在过多处理，可能会引起性能问题，容易造成资源浪费
 */
@ThreadSafe
public class SingletonExample6 {
    //构造方法私有化
    private SingletonExample6() {
        //这里会有资源的处理包括运算
    }
    private static SingletonExample6 instance=null;
    static{
        System.out.println("静态代码块执行了");
        instance=new SingletonExample6();
    }

    //如果放在静态代码块后面，那么instance将会先new SingletonExample6()然后再置为null
    //因为static变量和静态代码块在初始化的时候是按定义顺序的！
//    private static SingletonExample6 instance=null;

    public static SingletonExample6 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(SingletonExample6.getInstance());
        System.out.println(SingletonExample6.getInstance());
    }
}
