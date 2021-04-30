package com.mxxb.mythread.blockqueuecontrol.toastdemo;

/**
 * @author Viper
 * @description
 * @date 2021/4/29
 */

public class Toast {

    private final int id;
    private Status status = Status.DRY;

    public Toast(int idn) {
        id = idn;
    }

    public void butter() {
        status = Status.BUTTERED;
    }

    public void jam() {
        status = Status.JAMMED;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Toast " + id + ": " + status;
    }

    public enum Status {DRY, BUTTERED, JAMMED}
}