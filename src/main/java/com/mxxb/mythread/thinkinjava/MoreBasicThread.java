package com.mxxb.mythread.thinkinjava;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Viper
 * @description
 * @date 2021/4/26
 */

public class MoreBasicThread {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new LiftOff(), "A" + i).start();
            System.out.println("waiting for LiftOff,");
        }
        AtomicInteger atomicInteger = new AtomicInteger(123);
        atomicInteger.incrementAndGet();
        int i = atomicInteger.get();
        System.out.println("automicInteger i :" + i);
    }
}