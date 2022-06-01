package com.bobocode.lesson9;

public class DemoApp {

    public static void main(String[] args) {
        System.out.println("Hello from " + Thread.currentThread().getName());

        Thread thread = new Thread() {
            public void run() {
                System.out.println("Hello from " + Thread.currentThread().getName());
            }
        };

        thread.start();

        Runnable runnable = () -> {
            System.out.println("Hello from " + Thread.currentThread().getName());
        };
        runnable.run();


    }
}
