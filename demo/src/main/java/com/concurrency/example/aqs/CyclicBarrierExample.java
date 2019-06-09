package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CyclicBarrierExample {

    private static CyclicBarrier barrier = new CyclicBarrier(5);
    //参数可以增加一个Runnable,表示进入屏障之后先执行Runnable操作，向下执行所有线程后续操作
    //赛克利克百瑞尔
    private static CyclicBarrier barrier2 = new CyclicBarrier(5, ()->{
        log.info("callback is running!");
    });
    public static void main(String[] args) throws InterruptedException {

        ExecutorService exe = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            exe.execute(()->{
                try {
                    race(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        exe.shutdown();
    }

    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        /**
         * 当达到barrier设置的值之后才会往下执行，加参数可以设置等待多长时间之后直接向下执行，
         * 需要捕获所有异常才能不影响继续向下执行
         */
        barrier.await();
        log.info("{} continue", threadNum);
    }
}
