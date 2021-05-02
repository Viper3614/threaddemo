package com.mxxb.mythread.threadlocal;

import java.sql.SQLOutput;

/**
 * @author Viper
 * @description
 * @date 2021/5/1
 */

public class Account {
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public Account(String name){
        this.threadLocal.set(name);
        System.out.println("----当前线程name副本的值："+this.threadLocal.get());
    }

    public String getName() {
        return threadLocal.get();
    }

    public void setName(String name) {
        this.threadLocal.set(name);
    }
}