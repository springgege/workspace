package com.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;

/**
 * 写锁必须等待所有读锁释放之后才能执行
 * 在读操作更频繁的需求中容易造成写饥饿，一直写不进去数据
 */
@Slf4j
public class ReentrantReadWriteLockExample {

    private final Map<String, Data> map = new TreeMap<>();

    private final java.util.concurrent.locks.ReentrantReadWriteLock lock = new java.util.concurrent.locks.ReentrantReadWriteLock();

    private final Lock readLock = lock.readLock();

    private final Lock writeLock = lock.writeLock();

    public Data get(String key){
        readLock.lock();
        try{
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try{
            return map.keySet();
        }finally {
            readLock.unlock();
        }
    }

    public Data put(String key, Data value){
        writeLock.lock();
        try{
            return map.put(key, value);
        }finally {
            readLock.unlock();
        }
    }

    class Data {

    }
}
