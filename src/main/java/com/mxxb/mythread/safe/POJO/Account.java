package com.mxxb.mythread.safe.POJO;

/**
 * @author Viper
 * @description 银行账户
 * @date 2021/4/25
 */

public class Account {
    private String accountNo; //账户号
    private double balance;   // 账户余额

    public Account(String accountNo, double balance) {
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

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int hashcode() {
        return accountNo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj != null && obj.getClass() == Account.class) {
            Account target = (Account) obj;
            return target.accountNo.equals(accountNo);
        }
        return false;

    }
}