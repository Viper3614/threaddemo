package com.mxxb.mythread.controlprocess.lockstyle;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Viper
 * @description
 * @date 2021/4/27
 */

public class AccountByLock {
    private ReentrantLock lock = new ReentrantLock(); //定义显式的lock对象
    private Condition condition = lock.newCondition();//通过lock对象获取Condition对象
    private String accountNo;
    private double balance;
    private boolean flag = false;//账户中是否有存款
    private int count = 0;//取钱次数

    public AccountByLock(String accountNo, double balance) {
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
        if (obj != null && obj.getClass() == AccountByLock.class) {
            AccountByLock target = (AccountByLock) obj;
            return target.accountNo.equals(accountNo);
        }
        return false;
    }

    //取前方法draw
    public void draw(double drawAmount) {
        lock.lock();//通过显式的lock对象加锁
        try {
            if (!flag) { // flag为false，表示账户中还没有存钱
                condition.await();//线程等待。调用该方法的线程释放同步监视器的锁定和cpu的执行权
            } else {
                //取钱操作
                System.out.println(Thread.currentThread().getName() + ",取钱：" + drawAmount + " ,取钱次数：" + ++count);
                balance -= drawAmount;
                System.out.println("账户余额：" + balance);
                flag = false;
                condition.signalAll();//唤醒存钱的线程。lock对象中Condition实例的唤醒线程方法。类似Object类中的notify()方法。只有当前线程放弃该对象的锁定后，才可以执行唤醒操作
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); //在finally代码块中释放锁
        }

    }

    //存钱 deposit
    public void deposit(double depositAmount) {
        lock.lock();
        try {
            if (flag) { // flag为true,表示账户已经有存钱，该方法阻塞
                condition.await();//类似Object类中的wait()方法
            } else {
                //存钱操作
                System.out.println(Thread.currentThread().getName() + " 存钱：" + depositAmount);
                balance += depositAmount;
                flag = true;
                condition.signal();//唤醒取钱线程
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}