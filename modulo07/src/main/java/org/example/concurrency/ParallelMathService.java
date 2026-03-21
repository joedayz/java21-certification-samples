package org.example.concurrency;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ParallelMathService {

    public int sumSquares(List<Integer> values, ExecutorService executor) {
        try {
            List<Future<Integer>> futures = executor.invokeAll(values.stream()
                    .<java.util.concurrent.Callable<Integer>>map(v -> () -> v * v)
                    .toList());

            int sum = 0;
            for (Future<Integer> future : futures) {
                sum += future.get();
            }
            return sum;
        } catch (Exception e) {
            throw new IllegalStateException("No se pudo calcular la suma en paralelo", e);
        }
    }
}

