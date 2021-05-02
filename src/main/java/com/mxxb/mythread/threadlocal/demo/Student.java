package com.mxxb.mythread.threadlocal.demo;

/**
 * @author Viper
 * @description
 * @date 2021/5/1
 */

public class Student {
    private int age;
    private int name;

    public Student() {
    }

    public Student(int age, int name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name=" + name +
                '}';
    }
}