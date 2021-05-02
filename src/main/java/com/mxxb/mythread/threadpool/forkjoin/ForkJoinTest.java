package com.mxxb.mythread.threadpool.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @author Viper
 * @description
 * @date 2021/5/1
 */

public class ForkJoinTest extends RecursiveTask<Long> {

    private long[] members;
    private int from;
    private int to;
    private int threshod = 10;

    public ForkJoinTest(long[] members, int from, int to) {
        this.members = members;
        this.from = from;
        this.to = to;
    }

    public static void main(String[] args) {
        //ExecutorService workStealingPool = Executors.newWorkStealingPool(4);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long[] members = LongStream.rangeClosed(1, 100000000).toArray();
        Long result = forkJoinPool.invoke(new ForkJoinTest(members, 0, members.length - 1));
        System.out.println("结果：" + result);
        System.out.println("活跃线程数量：" + forkJoinPool.getActiveThreadCount());
        System.out.println("窃取线程数量：" + forkJoinPool.getStealCount());
    }

    @Override
    protected Long compute() {
        long total = 0;
        if (to - from < 10) {
            for (int i = from; i <= to; i++) {
                total += members[i];
            }
            return total;
        } else {
            int middle = (from + to) / 2;
            ForkJoinTest leftJoin = new ForkJoinTest(members, from, middle);
            ForkJoinTest rightJoin = new ForkJoinTest(members, middle+1, to);
            leftJoin.fork();
            rightJoin.fork();
            return leftJoin.join() + rightJoin.join();

        }
    }
}