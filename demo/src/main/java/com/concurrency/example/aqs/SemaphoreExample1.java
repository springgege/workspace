package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class SemaphoreExample1 {

    private final static int threadCount = 20;

    public static void main(String[] args) {
        ExecutorService exc = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exc.execute(()->{
                try{
                    //semaphore.tryAcquire() 方法尝试获取一个许可，获取到就执行，否则释放掉此次线程
                    //semaphore.tryAcquire(i,TimeUnit.SECONDS)//1秒内尝试获取一个许可，获取到就执行，否则释放掉此次线程
                    semaphore.acquire(3);//acquire没有入参默认每个线程占用一个许可，有参数则标识一个线程占用多少个许可！！
                    log.info("{}", "进来了！" + threadNum);
                    test(threadNum);
                    semaphore.release(3 );//表示每个线程执行完可以释放多少个许可！
                    log.info("{}", "出去了！" + threadNum);
                }catch(Exception e){
                    log.error("exception" , e);
                }
            });
        }
        exc.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }
}
