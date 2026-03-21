package org.example.concurrency;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Caso02_ExecutorServiceInvokeAll {

    public static void main(String[] args) throws Exception {
        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {
            List<Callable<Integer>> tasks = List.of(
                    () -> slowAdd(1, 2),
                    () -> slowAdd(10, 20),
                    () -> slowAdd(100, 200)
            );

            List<Future<Integer>> results = executor.invokeAll(tasks);
            int total = 0;
            for (Future<Integer> result : results) {
                total += result.get();
            }
            System.out.println("Total de resultados: " + total);
        }
    }

    private static int slowAdd(int a, int b) throws InterruptedException {
        Thread.sleep(100);
        return a + b;
    }
}

