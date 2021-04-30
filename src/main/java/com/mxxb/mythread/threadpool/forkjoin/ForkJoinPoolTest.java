package com.mxxb.mythread.threadpool.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;

/**
 * @author Viper
 * @description
 * @date 2021/4/30
 */

public class ForkJoinPoolTest extends RecursiveTask<Long> {

    private long[] numbers;
    private int from;
    private int to;

    public ForkJoinPoolTest(long[] numbers, int from, int to) {
        this.numbers = numbers;
        this.from = from;
        this.to = to;
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        long[] numbers = LongStream.rangeClosed(1, 100000000).toArray();
        Long result = forkJoinPool.invoke(new ForkJoinPoolTest(numbers, 0, numbers.length - 1));
        //forkJoinPool.submit(new ForkJoinPoolTest(numbers,0,numbers.length-1));
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        forkJoinPool.shutdown();
        System.out.println("活跃线程数量：" + forkJoinPool.getActiveThreadCount());
        System.out.println("窃取任务数量：" + forkJoinPool.getStealCount());
    }

    @Override
    protected Long compute() {
        long total = 0;
        if (from - to < 10) {
            for (int i = from; i <= to; i++) {
                total += numbers[i];
            }
            System.out.println("total:" +total);
            return total;
        } else {
            int middle = (from + to) / 2;
            ForkJoinPoolTest taskLeft = new ForkJoinPoolTest(numbers, from, middle);
            ForkJoinPoolTest taskRight = new ForkJoinPoolTest(numbers, middle + 1, to);
            taskLeft.fork();
            taskRight.fork();
            System.out.println("total:" +total);

            return taskLeft.join() + taskRight.join();

        }
    }
}