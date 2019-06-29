package com.bujidao.concurrency.immutable;

import com.bujidao.concurrency.annotation.NotThreadSafe;
import com.bujidao.concurrency.annotation.ThreadSafe;
import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.Map;

@ThreadSafe
public class ImmutableExample2 {
    //这里不能使用final
    private static Map<Integer,Integer> map= Maps.newHashMap();

    static {
        map.put(1,2);
        map.put(3,4);
        map.put(5,6);
        map=Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1,10);
        System.out.println(map.get(1));//使用了Collections.unmodifiableMap就不可修改里面的值
        //一旦修改就会抛出异常
    }

}
