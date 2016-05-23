package com.grass.test;

import org.codehaus.groovy.runtime.powerassert.SourceText;

import java.util.Random;

/**
 * Created by 韩亚辉 on 2016/5/21.
 */
public class NoVisiabilityTest {
    private static class ReadThread extends Thread{
        private boolean ready;
        private int number;

        @Override
        public void run() {
            while (!ready){
                number++;
            }
            System.out.println(number);
        }

        public void readyOn() {
            this.ready=true;
        }

        public static void main(String[] args) throws InterruptedException {
            ReadThread readThread=new ReadThread();
            readThread.start();
            Thread.sleep(200);
            readThread.readyOn();
            System.out.println(readThread.ready);
            double d=173793144832l;
            System.out.println(d/1024/1024/1024);
            for(int i=0;i<20;i++){
                Random random=new Random();
                int a = random.nextInt(10);
                System.out.println(a);
            }
        }
    }
}
