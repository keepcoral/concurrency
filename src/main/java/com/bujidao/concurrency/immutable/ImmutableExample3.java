package com.bujidao.concurrency.immutable;

import com.bujidao.concurrency.annotation.ThreadSafe;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import org.hibernate.validator.internal.util.stereotypes.Immutable;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@ThreadSafe
public class ImmutableExample3 {
    private static final ImmutableList list = ImmutableList.of(1, 2, 3);
    private static final ImmutableSet set = ImmutableSet.copyOf(list);
    private static final List<Integer> list2=ImmutableList.of(1,3,4);
    private static final Map<Integer,Integer> map= ImmutableMap
            .of(1,2,3,4,5,6);
    //第一个为Key第二个为value,以此类推
    private static final  Map<Integer,Integer> map2=ImmutableMap.<Integer,Integer>builder()
            .put(1,2).put(3,4).put(5,6).build();



    public static void main(String[] args) {
        System.out.println(list);
        System.out.println(set);
        System.out.println(list2);
        System.out.println(map);
//        list.add(4);//抛出异常，并将add方法改为废弃的方法
//        list2.add(10);//抛出异常，但是没有废弃
//        map.put(2,3);
        map2.put(1,3);
    }

}
