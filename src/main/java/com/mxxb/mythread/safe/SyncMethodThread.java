package com.mxxb.mythread.safe;

import com.mxxb.mythread.safe.POJO.SyncAccount;

/**
 * @author Viper
 * @description  同步方法
 * @date 2021/4/25
 */

public class SyncMethodThread extends Thread{
    private SyncAccount syncAccount;
    private double drawAmount;


    public SyncMethodThread(String name, SyncAccount syncAccount, double drawAmount) {
        super(name);
        this.syncAccount = syncAccount;
        this.drawAmount = drawAmount;
    }

    public static void main(String[] args) {

        System.out.println("-------同步方法---------");
        SyncAccount syncAccount = new SyncAccount("111", 1000);
        new SyncMethodThread("Sync_method-1", syncAccount, 600).start();
        new SyncMethodThread("Sync_method-2", syncAccount, 600).start();
    }

    @Override
    public void run() {
        syncAccount.draw(600);
    }
}