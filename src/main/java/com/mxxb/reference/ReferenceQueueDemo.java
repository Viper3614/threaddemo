package com.mxxb.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author Viper
 * @description
 * @date 2021/5/2
 */

public class ReferenceQueueDemo {
    public static void main(String[] args) {
        /*创建引用队列*/
        ReferenceQueue<SoftReference<int[]>> rq = new ReferenceQueue<SoftReference<int[]>>();

        /*创建一个软引用数组，每一个对象都是软引用类型*/
        SoftReference<int[]>[] srArr = new SoftReference[1000];

        for (int i = 0; i < srArr.length; i++) {
            srArr[i] = new SoftReference(new int[300000], rq);
        }

        /*（可能）在gc前保留下了三个强引用*/
        int[] arr1 = srArr[30].get();
        int[] arr2 = srArr[60].get();
        int[] arr3 = srArr[90].get();

        /*占用内存，会导致一次gc，使得只有软引用指向的对象被回收*/
        int[] strongRef = new int[200000000];

        Object x;
        int n = 0;
        while ((x = rq.poll()) != null) {
            int idx = 0;
            while (idx < srArr.length) {
                if (x == srArr[idx]) {
                    System.out.println("free " + x);
                    srArr[idx] = null; /*手动释放内存*/
                    n++;
                    break;
                }
                idx++;
            }
        }

        /*当然最简单的方法是通过isEnqueued()判断一个软引用方法是否在
         * 队列中，上面的方法只是举例
         int n = 0;
         for(int i = 0; i < srArr.length; i++){
            if(srArr[i].isEnqueued()){
                srArr[i] = null;
                n++;
            }
         }
        */
        System.out.println("recycle  " + n + "  SoftReference Object");
    }
}