package com.mxxb.mythread.blockqueuecontrol.toastdemo;

import com.mxxb.mythread.blockqueuecontrol.toastdemo.Toast.Status;

/**
 * @author Viper
 * @description
 * @date 2021/4/29
 */

public class Eater implements Runnable {

    private ToastQueue finishedQueue;
    private int counter = 0;

    public Eater(ToastQueue finished) {
        finishedQueue = finished;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = finishedQueue.take();
                if (t.getId() != counter++ || t.getStatus() != Status.JAMMED) {
                    System.out.println(">>>> ERROR :" + t);
                    System.exit(1);
                } else {
                    System.out.println("Chomp! " + t);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater off");
    }
}