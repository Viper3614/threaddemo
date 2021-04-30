package com.mxxb.mythread.blockqueuecontrol.delayqueuedemo;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Viper
 * @description
 * @date 2021/4/29
 */

public class DelayTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        DelayQueue<DelayedUser> queue = new DelayQueue<>();
        int messageCount = 2;
        long delayTime = 500;
        DelayedConsumer consumer = new DelayedConsumer(queue, messageCount);
        DelayedProducer producer = new DelayedProducer(queue, messageCount, delayTime);
        executor.submit(producer);
        executor.submit(consumer);

        executor.awaitTermination(3, TimeUnit.SECONDS);
        executor.shutdownNow();
    }
}