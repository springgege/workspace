package com.concurrency;

import com.concurrency.annoations.NotThreadSafe;

import java.util.concurrent.*;

@NotThreadSafe
class ConcurrencyTest {
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static int count = 0;

    public static void main(String[] args) throws Exception {
        //不推荐写法，新建线程池应该直接使其初始化！
        ExecutorService exc = Executors.newCachedThreadPool();
        //控制执行线程数
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal ; i++) {
            exc.execute(()->{
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    System.out.println("exception:" + e.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        exc.shutdown();
        System.out.println("执行完成：" + count);
    }

    private static void add(){
        count++;
        System.out.println(count);
    }
}
