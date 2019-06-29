package com.bujidao.concurrency.publish;

public class Escape {
    private int thisCanBeEscape = 0;

    public Escape() {
        //构造时初始化内部类InnerClass
        new InnerClass();
    }

    //定义一个内部类
    private class InnerClass {
        //定义一个构造方法
        public InnerClass() {
            //在这个内部类的实例里面包含了对[封装实例的隐含的引用]
            //这样在对象在没有被正确构造完成之前就会被发布，有可能有不安全的因素
            //在new InnerClass() 相当于启动了一个线程，不论是隐式还是显式
            //都会导致this 这个引用的逸出
            //新线程总会在所属对象构造完毕之前就已经看到它了
            System.out.println(Escape.this.thisCanBeEscape);
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
