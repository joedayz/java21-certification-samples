package org.example.concurrency;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadSafeCounterTest {

    @Test
    void incrementsAreSafeAcrossThreads() throws InterruptedException {
        ThreadSafeCounter counter = new ThreadSafeCounter();
        List<Thread> workers = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            Thread worker = Thread.ofVirtual().unstarted(() -> {
                for (int j = 0; j < 500; j++) {
                    counter.increment();
                }
            });
            workers.add(worker);
        }

        workers.forEach(Thread::start);
        for (Thread worker : workers) {
            worker.join();
        }

        assertEquals(10_000, counter.getValue());
    }
}

