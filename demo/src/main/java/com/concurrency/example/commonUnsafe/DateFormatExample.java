package com.concurrency.example.commonUnsafe;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.*;

@Slf4j
public class DateFormatExample {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    //线程安全的日期处理类
    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");

    public static int clientTotal = 5000;

    public static int ThreadTotal = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exc = Executors.newCachedThreadPool();
        //并发线程数控制
        final Semaphore semaphore = new Semaphore(ThreadTotal);
        //线程计数器
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            exc.execute(()->{
                try{
                    semaphore.acquire();
                    handle(count);
                    semaphore.release();
                }catch (Exception e){
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await( );
        exc.shutdown();
    }

    private static void handle(int i) {
        //线程不安全，会抛出异常java.lang.NumberFormatException: For input string: ""
        //simpleDateFormat.parse("20190405");
        //线程安全
        log.info("{},{}",i,DateTime.parse("20190405", dateTimeFormatter).toDate());
    }
}
