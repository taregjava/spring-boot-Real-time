package com.halfacode.spring_real_time_learning.util;

public class TestAnnonymous {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread is running via Anonymous Class!");
            }
        });

        t.start();
    }
}
