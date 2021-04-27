package com.mxxb.mythread.thinkinjava;

import java.io.IOException;

/**
 * @author Viper
 * @description
 * @date 2021/4/26
 */

public class ResponsiveUI extends Thread {
    private static volatile double d = 1;

    public ResponsiveUI() {
        setDaemon(true);
        start();
    }

    public static void main(String[] args) throws IOException {
        new ResponsiveUI();
        System.in.read();
        System.out.println(d);
    }

    @Override
    public void run() {
        while (true) {
            d = d + (Math.PI + Math.E) / d;
        }
    }
}

class UnresponsiveUI {
    private static volatile double d = 1;

    public UnresponsiveUI() throws IOException {
        while (d > 0) {
            d = d + (Math.PI + Math.E) / d;
            System.in.read();
        }
    }
}