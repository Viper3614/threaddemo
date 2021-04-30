package com.mxxb.mythread.blockqueuecontrol.delayqueuedemo;

import java.util.concurrent.DelayQueue;

/**
 * @author Viper
 * @description
 * @date 2021/4/29
 */

public class DelayedConsumer implements Runnable {

    private DelayQueue<DelayedUser> delayQueue;

    private int messageCount;

    public DelayedConsumer(DelayQueue<DelayedUser> blockingQueue, int messageCount) {
        this.delayQueue = blockingQueue;
        this.messageCount = messageCount;
    }

    @Override
    public void run() {
        for (int i = 0; i < messageCount; i++) {
            try {
                DelayedUser element = delayQueue.take();
                System.out.println("take :" + element);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}