package com.mxxb.mythread.blockqueuecontrol;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Viper
 * @description
 * @date 2021/4/27
 */

public class BlockQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        new Producer(blockingQueue).start();
        new Producer(blockingQueue).start();
        new Producer(blockingQueue).start();

        new Consumer(blockingQueue).start();
    }
}