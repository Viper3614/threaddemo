package com.mxxb.mythread.blockqueuecontrol;

import java.util.concurrent.BlockingQueue;

/**
 * @author Viper
 * @description
 * @date 2021/4/27
 */

public class Producer extends Thread {

    private BlockingQueue<String> blockingQueue;

    public Producer(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        String[] strArr = new String[]{
                "hello",
                "QQ",
                "ball"
        };
        for (int i = 0; i < 1000; i++) {
            System.out.println(getName() + " 生产者准备生产集合元素");
            try {
                Thread.sleep(2000);
                blockingQueue.put(strArr[i % 3]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName()+" 生产者集合元素完成");
    }
}