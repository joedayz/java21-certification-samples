package org.example.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Single Thread Scheduled Executor - Scheduler con un solo thread
 * 
 * Características:
 * - Combina SingleThreadExecutor + ScheduledExecutorService
 * - Solo 1 thread ejecuta todas las tareas programadas
 * - Garantiza ejecución secuencial de tareas programadas
 * - Ideal para tareas periódicas que deben ejecutarse en orden
 */
public class Caso15_SingleThreadScheduledExecutorDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Single Thread Scheduled Executor Demo ===\n");
        
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        
        System.out.println(">>> Todas las tareas programadas usan el MISMO thread\n");
        
        // Tarea 1: delay inicial
        scheduler.schedule(() -> {
            System.out.printf("[TASK-1] Ejecutada en %s después de 1s%n", 
                Thread.currentThread().getName());
        }, 1, TimeUnit.SECONDS);
        
        // Tarea 2: periódica cada 500ms
        scheduler.scheduleAtFixedRate(() -> {
            long time = System.currentTimeMillis() % 100000;
            System.out.printf("[%5d ms] [PERIODIC] Tick en %s%n", 
                time, Thread.currentThread().getName());
        }, 0, 500, TimeUnit.MILLISECONDS);
        
        // Tarea 3: con delay fijo
        scheduler.scheduleWithFixedDelay(() -> {
            long time = System.currentTimeMillis() % 100000;
            System.out.printf("[%5d ms] [DELAYED] Processing en %s%n", 
                time, Thread.currentThread().getName());
            try {
                Thread.sleep(300); // Simula trabajo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, 0, 700, TimeUnit.MILLISECONDS);
        
        // Dejar correr 5 segundos
        Thread.sleep(5000);
        
        System.out.println("\n>>> Observa que todas usan pool-1-thread-1 (mismo thread)\n");
        
        scheduler.shutdown();
        scheduler.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("✓ Completado");
    }
}
