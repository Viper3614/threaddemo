package com.mxxb.mythread.controlprocess.commonstyle;

/**
 * @author Viper
 * @description
 * @date 2021/4/27
 */

public class DrawThread extends Thread {
    private Accounts accounts;
    private double drawAmount;


    public DrawThread(String threadName, Accounts accounts, double drawAmount) {
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