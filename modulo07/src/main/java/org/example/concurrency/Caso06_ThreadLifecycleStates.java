package org.example.concurrency;

public class Caso06_ThreadLifecycleStates {

    public static void main(String[] args) throws InterruptedException {
        Runnable sharedTask = () -> {
            try {
                System.out.println("[task] Running on " + Thread.currentThread().getName());
                Thread.sleep(300);
                System.out.println("[task] Finishing " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread t1 = new Thread(sharedTask, "worker-1");
        Thread t2 = new Thread(sharedTask, "worker-2");

        // NEW: created but not started yet.
        System.out.println("t1 NEW? state=" + t1.getState() + ", alive=" + t1.isAlive());
        System.out.println("t2 NEW? state=" + t2.getState() + ", alive=" + t2.isAlive());

        t1.start();
        t2.start();

        // Give scheduler a moment to run both threads.
        Thread.sleep(50);
        System.out.println("t1 running state=" + t1.getState() + ", alive=" + t1.isAlive());
        System.out.println("t2 running state=" + t2.getState() + ", alive=" + t2.isAlive());

        t1.join();
        t2.join();

        // TERMINATED: execution completed.
        System.out.println("t1 done state=" + t1.getState() + ", alive=" + t1.isAlive());
        System.out.println("t2 done state=" + t2.getState() + ", alive=" + t2.isAlive());

        // Same Thread instance cannot be started twice.
        try {
            t1.start();
        } catch (IllegalThreadStateException ex) {
            System.out.println("Expected: cannot start same Thread twice -> " + ex.getClass().getSimpleName());
        }
    }
}

