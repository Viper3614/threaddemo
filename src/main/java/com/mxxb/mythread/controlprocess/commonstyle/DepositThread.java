package com.mxxb.mythread.controlprocess.commonstyle;

/**
 * @author Viper
 * @description
 * @date 2021/4/27
 */

public class DepositThread extends Thread {

    private Accounts accounts;
    private double depositAmount;

    public DepositThread(String threadName, Accounts accounts, double depositAmount) {
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