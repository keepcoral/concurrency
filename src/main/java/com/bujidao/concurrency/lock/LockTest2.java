package com.bujidao.concurrency.lock;

import com.bujidao.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
@ThreadSafe
public class LockTest2 {
    //内部封装了一个Map，不希望把所有的函数都暴露给别人
    //在操作过程中防止并发的问题，在读和写的时候分别加锁
    private final Map<String, Date> map = new TreeMap<>();

    private final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private final Lock readLock = reentrantReadWriteLock.readLock();

    private final Lock writeLock = reentrantReadWriteLock.writeLock();

    public Date get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public Date put(String key, Date date) {
        //在没有任何读写锁的情况才能进行写操作
        //现实的是悲观读取：在写的时候不允许有任何读的操作
        //容易出现读的情况特别多，然后写的线程遭遇饥饿
        writeLock.lock();
        try {
            return map.put(key, date);
        } finally {
            writeLock.unlock();
        }
    }
}
