package com.bujidao.concurrency.immutable;

import com.bujidao.concurrency.annotation.NotThreadSafe;
import com.fasterxml.jackson.databind.ser.std.MapSerializer;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

@NotThreadSafe
public class ImmutableExample1 {
    private static final Integer i=1;
    private static final String s="a";
    private static final Map<Integer,Integer> map= Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
    }

    public static void main(String[] args) {
//        i=2;  //赋值的时候出现编译错误
//        s="dsads";
//        map=new HashMap<>();
        map.put(1,10);
        System.out.println(map.get(1));//对象内的值被修改了，这样容易引发线程安全问题
        //对象内的值是可以修改的!
    }

    //用final修饰传入的变量同样也是不能修改的
    public void test(final int a){
//        a=100;
    }
}
