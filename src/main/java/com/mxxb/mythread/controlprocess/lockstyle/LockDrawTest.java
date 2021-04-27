package com.mxxb.mythread.controlprocess.lockstyle;

import com.mxxb.mythread.controlprocess.lockstyle.AccountByLock;
import com.mxxb.mythread.controlprocess.lockstyle.LockDepositThread;
import com.mxxb.mythread.controlprocess.lockstyle.LockDrawThread;

/**
 * @author Viper
 * @description
 * @date 2021/4/27
 */

public class LockDrawTest {
    public static void main(String[] args) {
        AccountByLock accounts = new AccountByLock("798", 0);
        new LockDrawThread("取钱人",accounts,500).start();

        /*
        *  多个存钱人执行存钱操作的话，程序最后被阻塞，存钱的线程等待取钱的线程notify()自己。但是取钱设置的次数只有100次，小于存钱的次数，存钱的线程wait()就会被阻塞。
        */
        new LockDepositThread("存钱人-1",accounts,500).start();
        new LockDepositThread("存钱人-2",accounts,500).start();
        new LockDepositThread("存钱人-3",accounts,500).start();
    }
}