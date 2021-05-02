package com.mxxb.reference;

import jdk.internal.org.objectweb.asm.TypeReference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * @author Viper
 * @description
 * @date 2021/5/2
 */

public class PhantomReferenceTest {
    public static void main(String[] args) {
        Object counter = new Object();
        ReferenceQueue<Object> refQueue = new ReferenceQueue<>();
        PhantomReference<Object> p = new PhantomReference<>(counter, refQueue);
        counter = null;
        System.gc();
        try {
            Reference<?> ref = refQueue.remove();
            if (ref!=null) {
                System.out.println("ref is not null");
            }
        } catch (Exception e ){
            System.out.println("Exception");
        }
    }
}