package com.bujidao.concurrency.publish;

import com.bujidao.concurrency.annotation.NotThreadSafe;

import java.util.Arrays;

@NotThreadSafe
public class UnsafePublish {
    private String[] states={"a","b","c"};

    //通过这个public方法发布了类的域
    //在类的任何外部线程都可以访问这些域，无法假设这些线程会不会修改这个域
    //造成类的状态错误
    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnsafePublish unsafePublish=new UnsafePublish();
        System.out.println(Arrays.toString(unsafePublish.states));
        unsafePublish.getStates()[0]="d";
        System.out.println(Arrays.toString(unsafePublish.states));
        //[a, b, c]
        //[d, b, c]
    }
}
