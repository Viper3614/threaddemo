package com.mxxb.mythread.blockqueuecontrol.toastdemo;

/**
 * @author Viper
 * @description
 * @date 2021/4/29
 */

public class Butter implements Runnable {

    private ToastQueue dryQueue, butteredQueue;

    public Butter(ToastQueue dry, ToastQueue buttered) {
        this.dryQueue = dry;
        this.butteredQueue = buttered;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                butteredQueue.put(t);
            } catch (InterruptedException e) {
                System.out.println("butter interrupted;");
            }
            System.out.println("butterer off");
        }
    }
}