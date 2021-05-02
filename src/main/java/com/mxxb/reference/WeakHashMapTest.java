package com.mxxb.reference;


import java.util.WeakHashMap;

/**
 * @author Viper
 * @description
 * @date 2021/5/2
 */

public class WeakHashMapTest {
    public static void main(String[] args) {
        /*
        * WeakHashMap原理：
        *   map中的Entry继承了WeakRefence，
        *   当Entry的key不再被使用（引用对象不可达）且被GC后，
        *   该Entry就会进入到ReferenceQueue。
        *   调用WeakHashMap的get()和put()方法，
        *   会清除无效key对应的Entry
        *
        */
        WeakHashMap<String, byte[]> whm = new WeakHashMap<>();
        String s1 = new String("s1");
        String s2 = new String("s2");
        String s3 = new String("s3");

        whm.put(s1,new byte[100]);
        whm.put(s2,new byte[100]);
        whm.put(s3,new byte[100]);

        s2 = null;
        s3 = null;

        System.out.println(whm.get("s1"));
        System.out.println(whm.get("s2"));
        System.out.println(whm.get("s3"));

        System.out.println("--------------");
        System.gc();
        System.out.println(whm.get("s1"));
        System.out.println(whm.get("s2"));
        System.out.println(whm.get("s3"));
    }
}