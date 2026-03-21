package org.example.concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Caso05_ProducerConsumerBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);

        Thread producer = Thread.ofVirtual().name("producer").start(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    queue.put(i);
                    System.out.println("Produciendo " + i);
                }
                queue.put(-1); // Poison pill
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = Thread.ofVirtual().name("consumer").start(() -> {
            try {
                while (true) {
                    int value = queue.take();
                    if (value == -1) {
                        break;
                    }
                    System.out.println("Consumiendo " + value);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.join();
        consumer.join();
        System.out.println("Caso05 finalizado.");
    }
}

