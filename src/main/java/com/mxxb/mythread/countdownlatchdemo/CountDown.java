package com.mxxb.mythread.countdownlatchdemo;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Viper
 * @description CountDownLatch 是JDK提供的多线程间通信的工具，让主线程指定任务完成的进度
 * @date 2021/4/29
 */

public class CountDown {
    public static void main(String[] args) {
        new CountDown().doSome(5);
    }

    public void doSome(int num) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,  //核心池大小
                15, //线程最多数量
                200, //线程最大空闲时间，超过该时间的将被回收
                TimeUnit.MILLISECONDS, //空闲时间的单位
                new ArrayBlockingQueue<Runnable>(10));//线程池的阻塞队列，保存执行任务的队列
        CountDownLatch n = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            Task task = new Task(i, n);
            executor.execute(task);
        }
        System.out.println("全部执行的任务数量：" + executor.getTaskCount());
        System.out.println("已经完成的任务数量：" + executor.getCompletedTaskCount());
        System.out.println("曾经创建过最大的线程数量：" + executor.getPoolSize());
        System.out.println("活动的线程数量：" + executor.getPoolSize());

        //等待所有线程执行完毕
        try {
            n.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();  // 关闭线程池
    }
}

class Task implements Runnable {
    CountDownLatch n;
    private int taskNum;

    public Task(int num, CountDownLatch n) {
        this.taskNum = num;
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+",hello,CountDownLatch! " + i);
        }
        n.countDown();
    }
}