package com.mxxb.mythread.safe.POJO;

/**
 * @author Viper
 * @description
 * @date 2021/4/25
 */

public class SyncAccount {
    private String accountNo; //账户号
    private double balance;   // 账户余额

    public SyncAccount(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    // 因账户余额在多线程情况并发情况下可能出现余额异常，这里不提供余额的set方法
    /*public void setBalance(double balance) {
        this.balance = balance;
    }*/

    /*
     *  通过同步方法修改账户余额
     */
    public synchronized void draw(double drawAmount) {
        if (balance >= drawAmount) {
            System.out.println(Thread.currentThread().getName() + " 取钱成功：" + drawAmount);
            balance -= drawAmount;
            System.out.println("\t余额为：" + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " 余额不足:" + balance);
        }
    }

    public int hashcode() {
        return accountNo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == SyncAccount.class) {
            SyncAccount target = (SyncAccount) obj;
            return target.accountNo.equals(accountNo);
        }
        return false;

    }
}