package com.concurrency.example.concurrent;

import java.util.List;
//并发容器JUC
import java.util.concurrent.*;

public class CopyOnWriteArrayListExample {
    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    private static List<Integer> list = new CopyOnWriteArrayList<Integer>();

    public static void main(String[] args) throws InterruptedException {
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
        System.out.println("执行完成：" + list.size());
    }

    private static void add() {
        list.add(1);
    }


}
