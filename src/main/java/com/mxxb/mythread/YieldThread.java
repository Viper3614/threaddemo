package com.mxxb.mythread;

/**
 * @author Viper
 * @description
 * @date 2021/4/24
 */

public class YieldThread extends Thread {
    public static void main(String[] args) {
        YieldThread yieldThread1 = new YieldThread();
        //yieldThread1.setPriority(Thread.MAX_PRIORITY); // setPriority()设置线程优先级
        yieldThread1.start();

        YieldThread yieldThread2 = new YieldThread();
       // yieldThread2.setPriority(Thread.MIN_PRIORITY); // setPriority()设置线程优先级
        yieldThread2.start();

    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(getName() + " " + i);
            if (i == 20) {
                Thread.yield();  // 当前线程让步，运行状态 -> 就绪状态
            }
        }
    }
}