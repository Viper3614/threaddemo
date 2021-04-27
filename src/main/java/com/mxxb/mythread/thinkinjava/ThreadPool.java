package com.mxxb.mythread.thinkinjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Viper
 * @description
 * @date 2021/4/26
 */

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            service.execute(new LiftOff());
        }
        service.shutdown();
    }
}