package org.example.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Caso04_CompletableFuturePipeline {

    public static void main(String[] args) {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            CompletableFuture<Integer> subtotalA = CompletableFuture.supplyAsync(() -> price("Laptop", 1200), executor);
            CompletableFuture<Integer> subtotalB = CompletableFuture.supplyAsync(() -> price("Mouse", 50), executor);

            CompletableFuture<Integer> total = subtotalA
                    .thenCombine(subtotalB, Integer::sum)
                    .thenApply(sum -> sum - discount(sum));

            System.out.println("Total con descuento: " + total.join());
        }
    }

    private static int price(String item, int base) {
        System.out.println("Calculando " + item + " en " + Thread.currentThread());
        return base;
    }

    private static int discount(int total) {
        return total >= 1_000 ? 100 : 0;
    }
}

