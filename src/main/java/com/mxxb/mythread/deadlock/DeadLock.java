package com.mxxb.mythread.deadlock;

/**
 * @author Viper
 * @description
 * @date 2021/4/27
 */

public class DeadLock implements Runnable {
    A a = new A();
    B b = new B();

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        new Thread(deadLock).start();
        deadLock.init();
    }

    public void init() {
        Thread.currentThread().setName("主线程");
        a.foo(b);
        System.out.println("进入了主线程之后");
    }

    @Override
    public void run() {
        Thread.currentThread().setName("副线程");
        b.bar(a);
        System.out.println("进入了副线程之后");
    }
}