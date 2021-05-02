package com.mxxb.mythread.threadlocal.demo;

import com.sun.media.sound.SoftTuning;

import java.util.Random;

/**
 * @author Viper
 * @description
 * @date 2021/5/1
 */

public class ThreadLocalDemo implements Runnable {

    /*
    * static静态变量：
    *   特点：
    *       被所有对象共享，
    *       在内存中只有一个副本
    *       在类初次加载的时候初始化
    *   static成员变量初始化顺序按照定义的顺序来进行初始化
    */
    private static ThreadLocal<Student> studentThreadLocal = new ThreadLocal<>();

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " is running ...");
        Random random = new Random();
        int age = random.nextInt(30);
        System.out.println(threadName + " set age is :" + age);
        Student stu = getStudent();
        stu.setAge(age);
        System.out.println(threadName + " is first get age:" + stu.getAge());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(threadName+" is second get age : " +stu.getAge());
    }

    private Student getStudent() {
        Student student = studentThreadLocal.get();
        if (student == null) {
            student = new Student();
            studentThreadLocal.set(student);
        }
        return student;
    }

    public static void main(String[] args) {
        ThreadLocalDemo t = new ThreadLocalDemo();
        new Thread(t,"thread A").start();
        new Thread(t,"thread B").start();
    }
}