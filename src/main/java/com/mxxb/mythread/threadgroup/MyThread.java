package com.mxxb.mythread.threadgroup;

/**
 * @author Viper
 * @description
 * @date 2021/4/28
 */

public class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public static void main(String[] args) {
        ThreadGroup mianGroup = Thread.currentThread().getThreadGroup();
        System.out.println("主线程组的名字：" + mianGroup.getName());
        System.out.println("主线程组是否是后台线程组：" + mianGroup.isDaemon());
        new MyThread("主线程组的线程").start();
        ThreadGroup newGroup = new ThreadGroup("新线程组");
        newGroup.setDaemon(true);

        System.out.println("newGroup是否是后台线程组：" + newGroup.isDaemon());
        MyThread t1 = new MyThread(newGroup, "newGroup组的线程甲");
        t1.start();
        new MyThread(newGroup, "newGroup组的线程乙").start();

    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println(getName() + " 线程i的变量：" + i);
        }
    }
}