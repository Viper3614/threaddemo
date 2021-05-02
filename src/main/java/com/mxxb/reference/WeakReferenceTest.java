package com.mxxb.reference;

import java.lang.ref.WeakReference;

/**
 * @author Viper
 * @description
 * @date 2021/5/2
 */

public class WeakReferenceTest {
    public static void main(String[] args) {
        WeakReference<String[]> weakReference = new WeakReference<>(new String[1000]);
        System.out.println("GC 之前：" + weakReference.get());
        System.gc();
        System.out.println("GC 之后：" + weakReference.get());
    }
}