package com.mxxb.mythread.blockqueuecontrol.delayqueuedemo;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Viper
 * @description
 * @date 2021/4/29
 */

public class DelayedUser implements Delayed {

    private String name;
    private long avaibleTime;

    public DelayedUser(String name, long delayTime) {
        this.name = name;
        this.avaibleTime = delayTime + System.currentTimeMillis();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diffTime = avaibleTime - System.currentTimeMillis();
        return unit.convert(diffTime, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int)(this.avaibleTime - ((DelayedUser) o).getAvaibleTime());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAvaibleTime() {
        return avaibleTime;
    }

    public void setAvaibleTime(long avaibleTime) {
        this.avaibleTime = avaibleTime;
    }

    @Override
    public String toString() {
        return "DelayedUser{" +
                "name='" + name + '\'' +
                ", avaibleTime=" + avaibleTime +
                '}';
    }
}