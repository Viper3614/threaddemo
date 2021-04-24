package com.mxxb.mythread;

/**
 * @author Viper
 * @description  Runnable方式创建多线程
 * @date 2021/4/23
 */

public class SecondThread implements Runnable {

    private int i;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                SecondThread target = new SecondThread();
                new Thread(target, "Runnable_1").start();
                new Thread(target, "Runnable_2").start();
            }
        }
    }

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }
}