package com.mxxb.mythread.blockqueuecontrol.delayqueuedemo;

import java.util.Random;
import java.util.concurrent.DelayQueue;

/**
 * @author Viper
 * @description
 * @date 2021/4/29
 */

public class DelayedProducer implements Runnable {

    private DelayQueue<DelayedUser> delayQueue;

    private Integer messageCount;

    private long delayedTime;

    public DelayedProducer(DelayQueue<DelayedUser> delayQueue, int messageCount, long delayedTime) {
        this.delayQueue = delayQueue;
        this.messageCount = messageCount;
        this.delayedTime = delayedTime;
    }

    @Override
    public void run() {
        for (int i = 0; i < messageCount; i++) {
            try {
                DelayedUser delayedUser = new DelayedUser(new Random().nextInt(1000) + "", delayedTime);
                System.out.println("put delayedUser :" + delayedUser);
                delayQueue.put(delayedUser);
                Thread.sleep(500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}