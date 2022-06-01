package com.bobocode.lesson9;

public class DemoApp2 {

    private static Object loc1 = new Object();
    private static Object loc2 = new Object();

    public static void main(String[] args) throws InterruptedException {

        Runnable r1 = () -> {
            synchronized (loc1) {
                synchronized (loc2) {
                    System.out.println("R1");
                }
            }
        };

        Runnable r2 = () -> {
            synchronized (loc2) {
                synchronized (loc1) {
                    System.out.println("R2");
                }
            }
        };
        Thread th1 = new Thread(r1);
        Thread th2 = new Thread(r2);
        th1.join();
        th2.join();
        r1.run();
        r2.run();
    }
}
