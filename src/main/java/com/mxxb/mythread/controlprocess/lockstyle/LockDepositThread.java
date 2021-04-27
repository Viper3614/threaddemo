package com.mxxb.mythread.controlprocess.lockstyle;

import com.mxxb.mythread.controlprocess.lockstyle.AccountByLock;

/**
 * @author Viper
 * @description
 * @date 2021/4/27
 */

public class LockDepositThread extends Thread {

    private AccountByLock accounts;
    private double depositAmount;

    public LockDepositThread(String threadName, AccountByLock accounts, double depositAmount) {
        super(threadName);
        this.accounts = accounts;
        this.depositAmount = depositAmount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            accounts.deposit(depositAmount);
        }
    }
}