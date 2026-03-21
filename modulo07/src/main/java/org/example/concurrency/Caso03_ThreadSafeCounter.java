package org.example.concurrency;

import java.util.ArrayList;
import java.util.List;

public class Caso03_ThreadSafeCounter {

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeCounter counter = new ThreadSafeCounter();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Thread worker = Thread.ofVirtual().name("counter-" + i).unstarted(() -> {
                for (int j = 0; j < 1_000; j++) {
                    counter.increment();
                }
            });
            threads.add(worker);
        }

        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Valor final esperado=10000, actual=" + counter.getValue());
    }
}

