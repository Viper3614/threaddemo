package com.mxxb.mythread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Viper
 * @description callable方式创建多线程
 * @date 2021/4/23
 */

public class ThirdThread {
    public static void main(String[] args) {
        /*
         *   使用Lambda表达式创建Callable对象，重写call()方法,call()方法作为线程执行体
         *   使用FutureTask对象包装Callable对象
         */
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            int i = 0;

            @Override
            public Integer call() throws Exception {
                for (; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + " " + i);
                }
                return i;
            }
        });
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 40) {
                try {
                    Thread.yield();
                    new Thread(task, "有返回值的线程_1").start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //获取返回值
        try {
            System.out.println("子线程的返回值：" + task.get()); // FutureTask对象的get()方法会导致主线程阻塞，直到call()方法结束并且返回为止。
            System.out.println("子线程是否执行完毕：" + task.isDone());
            System.out.println("子线程是否被取消：" + task.isCancelled());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}