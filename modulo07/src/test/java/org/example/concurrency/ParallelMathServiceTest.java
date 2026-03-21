package org.example.concurrency;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParallelMathServiceTest {

    @Test
    void sumSquaresUsesParallelTasks() {
        ParallelMathService service = new ParallelMathService();

        try (ExecutorService executor = Executors.newFixedThreadPool(4)) {
            int result = service.sumSquares(List.of(1, 2, 3, 4), executor);
            assertEquals(30, result);
        }
    }
}

