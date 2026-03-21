package org.example.concurrency;

public class ThreadSafeCounter {

    private int value;

    public synchronized void increment() {
        value++;
    }

    public synchronized int getValue() {
        return value;
    }
}

