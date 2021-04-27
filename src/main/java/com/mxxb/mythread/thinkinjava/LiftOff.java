package com.mxxb.mythread.thinkinjava;

/**
 * @author Viper
 * @description
 * @date 2021/4/26
 */

public class LiftOff implements Runnable {

    private static int taskCount = 0;
    private final int id = taskCount++;
    private int countDown = 10;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!)") + " "+Thread.currentThread().getName() + "). ";
    }


    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.print(status());
            Thread.yield();
        }
    }
}