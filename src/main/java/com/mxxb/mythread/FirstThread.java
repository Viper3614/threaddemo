package com.mxxb.mythread;

/**
 * @author Viper
 * @description  Thread方式创建多线程
 * @date 2021/4/23
 */

public class FirstThread extends Thread {

    private int i;

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            if (i == 20) {
                new FirstThread().start();
                new FirstThread().start();
            }
        }
    }

    @Override
    public void run() {
        for (; i < 100; i++) {
            System.out.println(this.getName() + " " + i);
        }
    }
}