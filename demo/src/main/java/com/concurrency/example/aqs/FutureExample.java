package com.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class FutureExample {
    static class MyCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            log.info("do something in callable");
            Thread.sleep(1000);
            return "Done";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exe = Executors.newCachedThreadPool();
        Future<String> future = exe.submit(new MyCallable());

        ExecutorService exe2 = Executors.newCachedThreadPool();
        FutureTask<String> task = new FutureTask<String>(()->{
            log.info("do something in callable");
            Thread.sleep(1000);
            return "Done";
        });
        exe2.execute(task);
        log.info("do something in main");
        Thread.sleep(1000);
        String result = future.get();
        log.info("result:{}", result);
        exe.shutdown();
        String result2 = task.get();
        log.info("result:{}", result);
        exe2.shutdown();
    }
}
