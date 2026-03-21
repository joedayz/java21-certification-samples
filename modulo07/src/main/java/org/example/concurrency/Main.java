package org.example.concurrency;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Implement Threads ===");

        // 1) Create class that extends Thread -> instantiate -> start
        Thread extendThread = new LateralExtendsThread();
        extendThread.start();

        // 2) Create class that implements Runnable -> pass to Thread -> start
        Runnable runnableTask = new LateralRunnable();
        Thread runnableThread = new Thread(runnableTask, "runnable-thread");
        runnableThread.start();

        // 3) Implement Runnable with lambda -> pass to Thread -> start
        Runnable lambdaTask = () -> printWork("lambda-runnable");
        Thread lambdaThread = new Thread(lambdaTask, "lambda-thread");
        lambdaThread.start();

        extendThread.join();
        runnableThread.join();
        lambdaThread.join();

        System.out.println("=== Demo terminada ===");
    }

    private static void printWork(String style) {
        System.out.printf("[%s] Ejecutando en %s%n", style, Thread.currentThread().getName());
    }

    static class LateralExtendsThread extends Thread {
        LateralExtendsThread() {
            super("extends-thread");
        }

        @Override
        public void run() {
            printWork("extends-thread");
        }
    }

    static class LateralRunnable implements Runnable {
        @Override
        public void run() {
            printWork("implements-runnable");
        }
    }
}
