package com.mxxb.mythread.controlprocess.commonstyle;

/**
 * @author Viper
 * @description 银行账户
 * @date 2021/4/27
 */

public class Accounts {
    private String accountNo;
    private double balance;
    private boolean flag = false;//账户中是否有存款
    private int count = 0;//取钱次数

    public Accounts(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    // 余额不可随意修改，所以不提供setBalance()方法
    public double getBalance() {
        return balance;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    public int hashcode() {
        return accountNo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == Accounts.class) {
            Accounts target = (Accounts) obj;
            return target.accountNo.equals(accountNo);
        }
        return false;
    }

    //取前方法draw
    public synchronized void draw(double drawAmount) {
        try {
            if (!flag) { // flag为false，表示账户中还没有存钱
                wait();
            } else {
                //取钱操作
                System.out.println(Thread.currentThread().getName() + ",取钱：" + drawAmount + " ,取钱次数："+ ++ count);
                balance -= drawAmount;
                System.out.println("账户余额：" + balance);
                flag = false;
                notifyAll();//唤醒其他线程
//                notify();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //存钱 deposit
    public synchronized void deposit(double depositAmount) {
        try {
            if (flag) { // flag为true,表示账户已经有存钱，该方法阻塞
                wait();
            } else {
                //存钱操作
                System.out.println(Thread.currentThread().getName() + " 存钱：" + depositAmount);
                balance += depositAmount;
                flag = true;
                notify();//唤醒取钱线程
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}