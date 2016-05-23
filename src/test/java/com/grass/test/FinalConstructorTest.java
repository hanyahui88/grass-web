package com.grass.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 韩亚辉 on 2016/5/23.
 */
public class FinalConstructorTest {
    static abstract class A {
        public A() {
            display();
        }

        public abstract void display();
    }
    static class B extends A{
        private int INT=100;
        private final int FINAL_INT=100;
        private final Integer FINAL_INTEGER=100;
        private String STRING="abc";
        private final String FINAL_STRING="abc";
        private final String FINAL_STRING1=new String("abc");
        private final List<String> FINAL_LIST=new ArrayList<>();
        public B(){
            super();
            System.out.println("abc");
        }
        @Override
        public void display() {
            System.out.println(INT);
            System.out.println(FINAL_INT);
            System.out.println(FINAL_INTEGER);
            System.out.println(STRING);
            System.out.println(FINAL_STRING);
            System.out.println(FINAL_STRING1);
            System.out.println(FINAL_LIST);
        }

        public static void main(String[] args) {
            new B();
        }
    }
}
