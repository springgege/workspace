package com.concurrency.example.aqs;

/**
 * ReentrantLock 能做更多的锁操作
 * 1/可重入锁
 * 2/锁的实现  synchronized是通过JVM实现的，ReentrantLock是JDK实现的
 * 3/性能区别  更建议使用synchronized
 * 4/功能区别 synchronized方便，ReentrantLock需要手动加锁与释放锁
 * 5/ReentrantLock独有功能
 *   1 synchronized是非公平锁，ReentrantLock可以设定是否是公平锁（锁等待优先级）
 *   2 ReentrantLock提供了Condition类，可以分组唤醒需要唤醒的线程
 *   3 提供了能够中断等待锁的线程机制，lock.lockInterruptibly()
 *
 */
public class ReentrantLockExample {

}
