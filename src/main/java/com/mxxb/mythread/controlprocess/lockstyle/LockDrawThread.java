package com.mxxb.mythread.controlprocess.lockstyle;

import com.mxxb.mythread.controlprocess.lockstyle.AccountByLock;

/**
 * @author Viper
 * @description
 * @date 2021/4/27
 */

public class LockDrawThread extends Thread {
    private AccountByLock accounts;
    private double drawAmount;


    public LockDrawThread(String threadName, AccountByLock accounts, double drawAmount) {
        super(threadName);
        this.accounts = accounts;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            accounts.draw(drawAmount);
        }
    }
}