package org.example.concurrency;

import java.util.concurrent.CountDownLatch;

public class Caso01_PlatformVsVirtualThreads {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch done = new CountDownLatch(2);

        Thread platform = Thread.ofPlatform().name("platform-demo").start(() -> {
            System.out.println("Platform thread -> " + Thread.currentThread());
            done.countDown();
        });

        Thread virtual = Thread.ofVirtual().name("virtual-demo").start(() -> {
            System.out.println("Virtual thread  -> " + Thread.currentThread());
            done.countDown();
        });

        done.await();
        platform.join();
        virtual.join();
        System.out.println("Caso01 finalizado.");
    }
}

