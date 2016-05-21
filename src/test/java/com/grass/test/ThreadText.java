package com.grass.test;

import org.junit.Test;

import java.util.Random;

/**
 * Created by 韩亚辉 on 2016/5/21.
 */
public class ThreadText {
    private static final int count = 1000000;

    static class MyThread extends Thread {
        private int start;
        private int end;
        private int result;
        private int[] ayyay;

        public MyThread(int[] array, int start, int end) {
            this.ayyay = array;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {
            for (int i = start; i < end; i++) {
                result += ayyay[i];
                if (result < 0) {
                    result &= Integer.MAX_VALUE;
                }
            }
        }

        public int getResult() {
            return result;

        }
    }

    @Test
    public void testThread() throws InterruptedException {
        int[] array = new int[count];
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            array[i] = Math.abs(random.nextInt());
        }
        System.out.println(array);
        long timeMillis = System.currentTimeMillis();
        MyThread myThread = new MyThread(array, 0, count);
        MyThread myThread1 = new MyThread(array, count / 2, count);
        myThread.start();
        myThread1.start();
        myThread.join();
        myThread1.join();
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis - timeMillis);
        System.out.println(myThread.getResult() );
        System.out.println(myThread1.getResult() );
        System.out.println((myThread.getResult() + myThread1.getResult()) & Integer.MAX_VALUE);

    }

}

