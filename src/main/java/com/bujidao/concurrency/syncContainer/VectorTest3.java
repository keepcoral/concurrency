package com.bujidao.concurrency.syncContainer;

import com.bujidao.concurrency.annotation.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.rmi.Remote;
import java.util.Iterator;
import java.util.Vector;

@Slf4j
@NotThreadSafe//标记该类不安全
public class VectorTest3 {

    private static Vector<Integer> vector=new Vector<>();
    //ConcurrentModificationException
    //对一个集合遍历时候，又做出了增删操作就会抛出ConcurrentModificationException
    public static void test1(){
        for(Integer i:vector){//foreach循环
            if(i.equals(3)){
                vector.remove(i);
            }
        }
    }
    //ConcurrentModificationException
    public static void test2(){
        Iterator<Integer> it=vector.iterator(); //迭代器遍历
        while(it.hasNext()){
            Integer i=it.next();
            if(i.equals(3)){
                vector.remove(i);
            }
        }
    }

    //运行成功
    public static void test3(){
        for(int i=0;i<vector.size();i++){
            if(vector.get(i)==3){
                vector.remove(i);
            }
        }
    }
    public static void main(String[] args) {
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test2();
    }
}
