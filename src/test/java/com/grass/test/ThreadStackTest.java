package com.grass.test;

/**
 * Created by 韩亚辉 on 2016/5/21.
 */
public class ThreadStackTest {

    private static StackTraceElement[] getStackByThread() {
        return Thread.currentThread().getStackTrace();
    }

    private static StackTraceElement[] getStackByException() {
        return new Exception().getStackTrace();
    }

    private static void printStack(StackTraceElement[] stackTraceElements) {
        for (int i = 0; i < stackTraceElements.length; i++) {
            System.out.println(stackTraceElements[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printStack(getStackByException());
        printStack(getStackByThread()       );
    }

}
