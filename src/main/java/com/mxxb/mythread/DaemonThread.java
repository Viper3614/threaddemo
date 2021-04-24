package com.mxxb.mythread;

/**
 * @author Viper
 * @description
 * @date 2021/4/24
 */

public class DaemonThread extends Thread {
    public static void main(String[] args) {
        DaemonThread daemon = new DaemonThread();
        daemon.setDaemon(true); //daemon设置为守护线程
        daemon.setName("daemon");
        daemon.start();
        System.out.println("daemon线程就绪");
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
        System.out.println(Thread.currentThread().getName() + ",执行结束，程序退出");
    }

    @Override
    public void run() {
        int i = 0;
        for (; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}