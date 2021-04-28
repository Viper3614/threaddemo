package com.mxxb.mythread.threadexception;

/**
 * @author Viper
 * @description
 * @date 2021/4/28
 */

public class MyExHandler implements Thread.UncaughtExceptionHandler { //
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(t + " 线程出现了异常：" + e);
    }
}

class ExHandler {
    public static void main(String[] args) {
       // Thread.currentThread().setUncaughtExceptionHandler(new MyExHandler()); // 设置线程异常处理器
        Thread.setDefaultUncaughtExceptionHandler(new MyExHandler());//为该线程类的所有线程实例设置异常处理器
        throw new RuntimeException("异常");
    }
}