package com.mxxb.mythread.blockqueuecontrol;

import java.util.concurrent.BlockingQueue;

/**
 * @author Viper
 * @description
 * @date 2021/4/27
 */

public class Consumer extends Thread {

    private BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println(getName() + "消费者准备消费集合元素");
            try {
                Thread.sleep(2000);
                blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "消费者消费完成" + blockingQueue);
        }
    }
}