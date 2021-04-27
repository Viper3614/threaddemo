package com.mxxb.mythread.safe;

import com.mxxb.mythread.safe.POJO.Account;
import com.mxxb.mythread.safe.POJO.SyncAccount;

/**
 * @author Viper
 * @description
 * @date 2021/4/25
 */

public class DrawThread extends Thread {

    private Account account;
    private double drawAmount;

    public DrawThread(String name, Account account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    public static void main(String[] args) {
        Account account = new Account("123", 1000);
        new DrawThread("A", account, 800).start();
        new DrawThread("B", account, 800).start();

    }

    @Override
    public void run() {
        /*
         * 同步监视器：阻止多个线程对同一个资源进行并发访问
         */
        synchronized (account) {
            if (account.getBalance() >= drawAmount) {
                System.out.println(System.currentTimeMillis() + "," + getName() + "取前成功，取前金额为：" + drawAmount);
                account.setBalance(account.getBalance() - drawAmount);
                System.out.println("当前余额为：" + account.getBalance());
                System.out.println("------------------------");
            } else {
                System.out.println(getName() + "取钱失败，余额不足，");
            }
        }
    }

}