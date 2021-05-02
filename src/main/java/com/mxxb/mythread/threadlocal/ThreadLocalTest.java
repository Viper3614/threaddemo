package com.mxxb.mythread.threadlocal;

/**
 * @author Viper
 * @description
 * @date 2021/5/1
 */

public class ThreadLocalTest extends Thread {
    private Account account;

    public ThreadLocalTest(Account account, String name) {
        super(name);
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i == 6) {
                account.setName(getName());
            }
            System.out.println(account.getName()+" 账户i的值："+i);
        }
    }

    public static void main(String[] args) {
        Account account = new Account("init name");
        /*
        *  两个线程共享一个account对象。
        *  账户name是threadLocal类型，所以每个线程都完全拥有各自的账户名副本。
        *  i=6之后，会出现两个线程访问同一个账户时不同的账户名。
        */
        new ThreadLocalTest(account,"甲").start();
        new ThreadLocalTest(account,"乙").start();
    }
}