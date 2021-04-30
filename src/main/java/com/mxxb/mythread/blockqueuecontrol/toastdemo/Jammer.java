package com.mxxb.mythread.blockqueuecontrol.toastdemo;

/**
 * @author Viper
 * @description
 * @date 2021/4/29
 */

public class Jammer implements Runnable {

    private ToastQueue butterQueue, finishQueue;

    public Jammer(ToastQueue buttered, ToastQueue finished) {
        butterQueue = buttered;
        finishQueue = finished;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Toast t = butterQueue.take();
                t.jam();
                System.out.println(t);
                finishQueue.put(t);
            } catch (InterruptedException e) {
                System.out.println("Jammer interrupted");
            }
            System.out.println("Jammer off");
        }
    }
}