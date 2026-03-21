package org.example.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Single Thread Executor - Un solo thread trabajador
 * 
 * Características:
 * - Solo 1 thread ejecuta todas las tareas
 * - Garantiza ejecución secuencial (FIFO)
 * - Si el thread muere por excepción, se crea uno nuevo
 * - Ideal para tareas que deben ejecutarse en orden
 */
public class Caso10_SingleThreadExecutorDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Single Thread Executor Demo ===\n");
        
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        System.out.println(">>> Enviando 5 tareas - se ejecutarán secuencialmente\n");
        
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.execute(() -> {
                String threadName = Thread.currentThread().getName();
                long timestamp = System.currentTimeMillis() % 100000;
                
                System.out.printf("[%5d ms] Tarea %d INICIO en %s%n", 
                    timestamp, taskId, threadName);
                
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                timestamp = System.currentTimeMillis() % 100000;
                System.out.printf("[%5d ms] Tarea %d FIN en %s%n", 
                    timestamp, taskId, threadName);
            });
        }
        
        System.out.println("\n>>> Todas las tareas usan el MISMO thread y se ejecutan en ORDEN\n");
        
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("✓ Completado");
    }
}
